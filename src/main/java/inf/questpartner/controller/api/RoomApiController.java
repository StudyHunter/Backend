package inf.questpartner.controller.api;

import inf.questpartner.controller.response.CreateRoomResponse;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.service.RoomService;
import inf.questpartner.service.UserService;
import inf.questpartner.util.validation.argumentResolver.CurrentUser;
import inf.questpartner.util.validation.argumentResolver.Login;
import inf.questpartner.util.validation.dto.SessionUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateRoomResponse createRoom(@Validated @RequestBody CreateRoomRequest dto) { //@CurrentUser SessionUser sessionUser

        User user = userService.findByNickname("dawn0");
        Long id = roomService.createRoom(user, dto);
        return new CreateRoomResponse(id);
    }



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
