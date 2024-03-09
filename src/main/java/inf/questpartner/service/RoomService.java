package inf.questpartner.service;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
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

    public void deleteRoom(Long id) {
        Room room = findById(id);
        roomRepository.delete(room);
    }



    @Transactional(readOnly = true)
    public Page<ResRoomPreview> sort(RoomSearchCondition condition, Pageable pageable) {
        Page<Room> rooms = roomRepository.searchPageSort(condition, pageable);
        return ResRoomPreview.convert(rooms);
    }


}