package inf.questpartner.service;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.StartTimeDto;
import inf.questpartner.dto.room.StudyTokenDto;
import inf.questpartner.dto.room.req.CreateRoomRequest;
import inf.questpartner.dto.room.res.ResRoomCreate;
import inf.questpartner.dto.room.res.ResRoomEnter;
import inf.questpartner.dto.room.res.ResRoomPreview;
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

@Slf4j

@Transactional
@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomHashTagRepository hashTagRepository;
    private final UserRepository userRepository;

    public ResRoomCreate createRoom(CreateRoomRequest req, User user) {
        User hostUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        Room room = roomRepository.save(req.toRoomEntity(hostUser.getEmail()));
        room.createChatRoom(new ChattingRoom());

        for (TagOption tag : req.getTags()) {
            RoomHashTag hashTag = hashTagRepository.save(new RoomHashTag(room, tag));
            room.addHashTag(hashTag);
        }

        //방장 참여자 정보에 넣어야 한다.
        room.addParticipant(hostUser);

        return ResRoomCreate.fromEntity(room);
    }

    @Transactional(readOnly = true)
    public ResRoomEnter enterRoom(Long id, User user) {
        User enterUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        Room room = findById(id);
        room.addParticipant(enterUser);

        for (User participant : room.getParticipants()) {
            log.info("participant nickname = {}", participant.getNickname());
        }
        return ResRoomEnter.fromEntity(room);
    }


    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundRoomException("존재하지 않는 방입니다."));
    }

    public void deleteRoom(Long id) {
        Room room = findById(id);
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

        int studyToken = (int) (studyTime / 30);
        room.getParticipants().forEach(participant -> participant.updateStudyToken(studyToken));
        return new StudyTokenDto(studyToken);
    }

    //    시작 시간과 종료 시간을 분으로 계산
    private Integer calculateStudyTimer(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return (int) duration.toMinutes();
    }

    @Transactional(readOnly = true)
    public Page<ResRoomPreview> sort(RoomSearchCondition condition, Pageable pageable) {
        Page<Room> rooms = roomRepository.searchPageSort(condition, pageable);
        return ResRoomPreview.convert(rooms);
    }

    /*
    // 취향 방 추천
    @Transactional(readOnly = true)
    public List<RoomTag> recommendLogic(UserWishTag user, List<RoomTag> roomTags) {
        for (RoomTag roomTag : roomTags) {
            double matchingScore = calculateMatchingScore(user, roomTag);
            roomTag.setMatchingScore(matchingScore);
        }

        List<RoomTag> modifiableRoomTags = new ArrayList<>(roomTags);
        modifiableRoomTags.sort(Comparator.comparingDouble(RoomTag::getMatchingScore).reversed());

        return Collections.unmodifiableList(modifiableRoomTags);
    }

    private static double calculateMatchingScore(UserWishTag userWishTag, RoomTag roomTag) {
        // 예상 기간 매칭 점수 계산
        double scheduleScore = calculateScheduleScore(userWishTag.getExpectedSchedule(), roomTag.getExpectedSchedule());

        // 방 태그 매칭 점수 계산
        double tagScore = calculateTagScore(userWishTag.getTagList(), roomTag.getTagList());

        // 종합적인 매칭 점수 반환 (가중치 적용 가능)
        return (scheduleScore + tagScore) / 2;
    }

    private static double calculateScheduleScore(int userExpectedSchedule, int roomExpectedSchedule) {
        // 예상 기간이 얼마나 일치하는지 계산
        int difference = Math.abs(userExpectedSchedule - roomExpectedSchedule);
        return 1.0 / (1 + difference); // 간단한 매칭 점수 계산 방법
    }

    private static double calculateTagScore(List<TagOption> userTags, List<TagOption> roomTags) {
        // 두 리스트에서 공통으로 포함된 태그의 개수 계산
        long commonTagsCount = userTags.stream()
                .filter(roomTags::contains)
                .count();

        // 두 리스트 중 크기가 더 작은 쪽의 태그 개수를 기준으로 점수 계산
        int smallerListSize = Math.min(userTags.size(), roomTags.size());
        return (double) commonTagsCount / smallerListSize;
    }

     */

}