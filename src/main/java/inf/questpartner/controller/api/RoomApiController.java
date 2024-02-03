package inf.questpartner.controller.api;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.CreateRoomReponse;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.service.RoomService;
import inf.questpartner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateRoomReponse createRoom(@Validated @RequestBody CreateRoomRequest dto){
        User user = userService.findByNickname("dawn0");
        Long id = roomService.createRoom(user, dto);
        return new CreateRoomReponse(id);
    }
}
