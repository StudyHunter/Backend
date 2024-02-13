package inf.questpartner.controller.api;

import inf.questpartner.controller.response.CreateRoomResponse;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.repository.room.RoomRepository;
import inf.questpartner.service.RoomService;
import inf.questpartner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RoomRepository roomRepository;

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


    //TODO: apiController 형식으로 변경하기
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    @PutMapping("/update")
    public Room updateRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        Room room = createRoomRequest.toEntity();
        Room target = roomRepository.findById(room.getId()).orElse(null);
        if (target != null) {
            return roomRepository.save(room);
        }
        return null;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return "Room with id " + id + " deleted successfully.";
    }
}