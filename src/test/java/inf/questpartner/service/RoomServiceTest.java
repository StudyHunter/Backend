package inf.questpartner.service;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.UserWishTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.dto.users.SaveRequest;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.users.NotFoundUserException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired RoomService roomService;
    @Autowired UserService userService;
    @Autowired
    UserRepository userRepository;

    /**
     * 테스트에 사용할 DTO 선언을 먼저 해둔다
     *
     *
     */
    private SaveRequest createUserDto() {
        return SaveRequest.builder()
                .nickname("dawn")
                .email("dawn@naver.com")
                .password("!1234")
                .build();
    }

    private CreateRoomRequest createRoomDtoA(String author) {
        CreateRoomRequest dto = new CreateRoomRequest();
        dto.setAuthor(author);
        dto.setTitle("deep learning study");
        dto.setExpectedUsers(3);
        dto.setExpectedSchedule(6);
        dto.setTags(List.of(TagOption.BOOKREVIEW.name(), TagOption.DEVELOP.name()));
        dto.setRoomType(RoomType.STUDY.name());
        dto.setThumbnail(RoomThumbnail.THUMB_CODING_VER1.name());
        return dto;
    }

    private CreateRoomRequest createRoomDtoB(String author) {
        CreateRoomRequest dto = new CreateRoomRequest();
        dto.setAuthor(author);
        dto.setTitle("winter vacation project team");
        dto.setExpectedUsers(5);
        dto.setExpectedSchedule(30);
        dto.setTags(List.of(TagOption.TEAMPROJECT.name(), TagOption.JAVA.name(), TagOption.JAVASCRIPT.name()));
        dto.setRoomType(RoomType.PROJECT.name());
        dto.setThumbnail(RoomThumbnail.THUMB_CODING_VER1.name());
        return dto;
    }

    @Test
    void test() {

        //when : user가 방 2개를 만들었는데
        userService.save(createUserDto());
        User user = userRepository.findByEmail(createUserDto().getEmail())
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        CreateRoomRequest dto1 = createRoomDtoA(user.getNickname());
        CreateRoomRequest dto2 = createRoomDtoB(user.getNickname());

        Long room1_id = roomService.createRoom(user, dto1);
        Long room2_id = roomService.createRoom(user, dto2);

        Room room1 = roomService.findById(room1_id);
        Room room2 = roomService.findById(room2_id);
        List<Room> roomList = List.of(room1, room2);

        // then0 : user를 닉네임으로도 찾을 수 있다.
        User findUser = userService.findByNickname(user.getNickname());
        assertThat(user.getNickname()).isEqualTo(findUser.getNickname());

        // then1 : 방이 잘 생성되었는지?
        assertThat(room1.getRoomType()).isEqualTo(RoomType.STUDY);

        // then2 : 태그로 방을 조회하면 잘 동작되는지?
        List<TagOption> searchTag = List.of(TagOption.TEAMPROJECT.BOOKREVIEW);

        Set<Room> roomsByTag = roomService.findRoomsByTag(searchTag, roomList);
        for (Room room : roomsByTag) {
            System.out.println(room.toString());
        }

        log.info("[check]==");
        List<TagOption> searchTag2 = List.of(TagOption.TEAMPROJECT, TagOption.PYTHON);
        Set<Room> roomsByTag2 = roomService.findRoomsByTag(searchTag2, roomList);
        for (Room room : roomsByTag2) {
            log.info(room.toString());
        }

    }

    /*

    @Test
    void tagTest() {

        List<TagOption> optionA = List.of(TagOption.BOOKREVIEW, TagOption.PYTHON, TagOption.INTERVIEW);
        List<TagOption> optionB = List.of(TagOption.TEAMPROJECT, TagOption.TEAMPROJECT, TagOption.DEEPLEARNING, TagOption.PYTHON);
        List<TagOption> optionC = List.of(TagOption.JAVA, TagOption.SPRING, TagOption.DEVELOP);

        CreateRoomRequest dto1 = new CreateRoomRequest("userA", optionA, RoomType.STUDY);
        CreateRoomRequest dto2 = new CreateRoomRequest("userB", optionB, RoomType.PROJECT);

        Room room1 = roomService.createRoom(dto1);
        Room room2 = roomService.createRoom(dto2);
        List<Room> rooms = List.of(room1, room2);

        List<TagOption> wishA = List.of(TagOption.BOOKREVIEW, TagOption.PYTHON);

        RoomTag roomTag1 = new RoomTag(3, 25, optionA);
        RoomTag roomTag2 = new RoomTag(6, 40, optionB);
        RoomTag roomTag3 = new RoomTag(9, 14, optionC);

        UserWishTag wishTag = new UserWishTag(3, 20, wishA);
        List<RoomTag> roomList = List.of(roomTag1, roomTag2, roomTag3);

        List<RoomTag> roomTags = roomService.recommendLogic(wishTag, roomList);

        for (RoomTag roomTag : roomTags) {
            System.out.println(roomTag);
        }
    }

     */

}