package inf.questpartner.controller.api;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.service.RoomService;
import inf.questpartner.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;
    private final UserService userService;

    // 검색 조회
    @GetMapping
    public Set<Room> findRoomByTag(@RequestParam List<TagOption> tags) {
        List<Room> roomList = roomService.findAll();
        return roomService.findRoomsByTag(tags, roomList);
    }

    // 데이터 기반으로 추천 받기
    @GetMapping("/recommend")
    public List<RoomTag> recommendRooms(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        List<RoomTag> roomTags = roomService.findRoomTags();

        return roomService.recommendLogic(user.toUserWishDto(), roomTags);
    }


}
