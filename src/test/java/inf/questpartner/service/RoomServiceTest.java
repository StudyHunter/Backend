package inf.questpartner.service;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.UserWishTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;


@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired RoomService roomService;


    @Test
    void test() {

        List<TagOption> optionA = List.of(TagOption.BOOKREVIEW, TagOption.PYTHON, TagOption.INTERVIEW);
        List<TagOption> optionB = List.of(TagOption.TEAMPROJECT, TagOption.TEAMPROJECT, TagOption.DEEPLEARNING, TagOption.PYTHON);

        CreateRoomRequest dto1 = new CreateRoomRequest("userA", optionA, RoomType.STUDY);
        CreateRoomRequest dto2 = new CreateRoomRequest("userB", optionB, RoomType.PROJECT);

        Room room1 = roomService.createRoom(dto1);
        Room room2 = roomService.createRoom(dto2);
        List<Room> roomList = List.of(room1, room2);


        List<TagOption> searchTag = List.of(TagOption.TEAMPROJECT.BOOKREVIEW);

        Set<Room> roomsByTag = roomService.findRoomsByTag(searchTag, roomList);
        for (Room room : roomsByTag) {
            System.out.println(room.toString());
        }

        System.out.println("[test2]==");
        List<TagOption> searchTag2 = List.of(TagOption.TEAMPROJECT, TagOption.PYTHON);
        Set<Room> roomsByTag2 = roomService.findRoomsByTag(searchTag2, roomList);
        for (Room room : roomsByTag2) {
            System.out.println(room.toString());
        }

    }

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

}