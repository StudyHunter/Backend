package inf.questpartner.controller.api;

import inf.questpartner.controller.response.CreateRoomResponse;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.service.RoomService;
import inf.questpartner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public CreateRoomResponse createRoom(@Validated @RequestBody CreateRoomRequest dto) {
        //@CurrentUser SessionUser sessionUser

        User user = userService.findByNickname("dawn0");
        Long id = roomService.createRoom(user, dto);
        return new CreateRoomResponse(id);
    }

//    룸 스타트 버튼 클릭 시 (스톱워치 작동하는 건 구현 추가해야될까요)
    @PostMapping("/{roomId}/start")
    public void startRoomTime(@PathVariable Long roomId) {
        roomService.startRoom(roomId);
    }

    @PostMapping("/{roomId}/update")
    public void endRoomTime(@PathVariable Long roomId, @RequestParam LocalDateTime currentTime) {
        roomService.updateRoomTime(roomId, currentTime);
    }

    // 검색 조회
//    @GetMapping
//    public Set<Room> findRoomByTag(@RequestParam List<TagOption> tags) {
//        List<Room> roomList = roomService.findAll();
//        return roomService.findRoomsByTag(tags, roomList);
//    }
//
//    // 데이터 기반으로 추천 받기
//    @GetMapping("/recommend")
//    public List<RoomTag> recommendRooms(@RequestParam(value = "id") Long id) {
//        User user = userService.findById(id);
//        List<RoomTag> roomTags = roomService.findRoomTags();
//
//        return roomService.recommendLogic(user.toUserWishDto(), roomTags);
//    }
}
