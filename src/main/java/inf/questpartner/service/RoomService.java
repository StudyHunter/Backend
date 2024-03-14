package inf.questpartner.service;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.StartTimeDto;
import inf.questpartner.dto.room.StudyTokenDto;
import inf.questpartner.dto.room.req.CreateRoomRequest;
import inf.questpartner.dto.room.req.UpdateRoomRequest;
import inf.questpartner.dto.room.res.ResRoomCreate;
import inf.questpartner.dto.room.res.ResRoomEnter;
import inf.questpartner.dto.room.res.ResRoomPreview;
import inf.questpartner.dto.room.res.ResRoomUpdate;
import inf.questpartner.repository.room.RoomHashTagRepository;
import inf.questpartner.repository.room.RoomRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.ResourceNotFoundException;
import inf.questpartner.util.exception.room.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j

@Transactional
@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomHashTagRepository hashTagRepository;
    private final UserRepository userRepository;
    private final ChatBoxService chatBoxService;

    public ResRoomCreate createRoom(CreateRoomRequest req, User user) {
        // 방 생성하는 host(user) 엔티티 생성
        User hostUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        // 스터디 방 생성
        Room room = roomRepository.save(req.toRoomEntity(hostUser.getEmail()));

        // 스터디 방 태그 설정
        for (TagOption tag : req.getTags()) {
            RoomHashTag hashTag = hashTagRepository.save(new RoomHashTag(room, tag));
            room.addHashTag(hashTag);
        }

        //방장 참여자 정보에 넣어야 한다.
        room.addParticipant(hostUser);

        //채팅창 활성화
       Long chatBoxId = chatBoxService.createRoom(hostUser);
       room.settingChatBox(chatBoxId);

        return ResRoomCreate.fromEntity(room);
    }


    public ResRoomEnter enterRoom(Long id, User user) {
        User enterUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        Room room = findById(id);
        room.addParticipant(enterUser);

        for (User participant : room.getParticipants()) {
            log.info("participant nickname = {}", participant.getNickname());
        }

        // 스터디 방에 입장하면 채팅창에도 참여할 수 있다.
        chatBoxService.joinToChatRoom(enterUser.getEmail(), room.getStudyChatBoxId());

        return ResRoomEnter.fromEntity(room);
    }


    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundRoomException("존재하지 않는 방입니다."));
    }

    //수정
    @Transactional
    public ResRoomUpdate updateRoom(Long roomId, UpdateRoomRequest req, User user) {
        User hostUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        //DTO -> 엔티티로 변환
        Room room = req.toRoomEntity(hostUser.getEmail());

        // 방 ID를 사용하여 해당 방을 데이터베이스에서 조회
        Room target = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundRoomException("존재하지 않는 방입니다."));
        // 요청으로부터 받은 정보로 방 정보 업데이트
        target.patch(room);

        // 기존에 저장된 태그들을 모두 삭제
        Set<RoomHashTag> oldTags = target.getRoomHashTags();
        hashTagRepository.deleteAll(oldTags);
        target.getRoomHashTags().clear();

        // 새로 받은 태그로 저장
        for (TagOption tag : req.getTags()) {
            RoomHashTag hashTag = new RoomHashTag(target, tag);
            target.addHashTag(hashTag);
        }

        // 업데이트된 방 정보를 저장하고 반환
        return ResRoomUpdate.fromEntity(target);
    }

    public void deleteRoom(Long roomId) {
        Room room = findById(roomId);
        roomRepository.delete(room);
    }

    public StartTimeDto startStudy(Long id) {
        Room room = findById(id);
        LocalDateTime startTime = LocalDateTime.now();
        room.startRoomTime(startTime);
        return new StartTimeDto(startTime);
    }

    //    종료 시 시간과 방의 시작 시간을 계산하여 유저마다 적용
    public StudyTokenDto endStudy(Long id) {
        Room room = findById(id);
        LocalDateTime endTime = LocalDateTime.now();

        Integer studyTime = calculateStudyTimer(room.getStartTime(), endTime);
        room.getParticipants().forEach(participant -> participant.updateTotalTime(studyTime));
        room.endRoomTime(studyTime);

        int studyToken = studyTime / 30;
        room.getParticipants().forEach(participant -> participant.updateStudyToken(studyToken));
        return new StudyTokenDto(studyToken);
    }

    @Transactional(readOnly = true)
    public Page<ResRoomPreview> findAll(Pageable pageable) {
        Page<Room> rooms = roomRepository.findAllWithTagAndUser(pageable);
        return ResRoomPreview.convert(rooms);
    }
    //    시작 시간과 종료 시간을 분으로 계산
    private Integer calculateStudyTimer(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return (int) duration.toMinutes();
    }

    @Transactional(readOnly = true)
    public Page<ResRoomPreview> sort(RoomSearchCondition condition, Pageable pageable) {
        Page<Room> rooms = roomRepository.findByTagOption(condition, pageable);
        return ResRoomPreview.convert(rooms);
    }


}