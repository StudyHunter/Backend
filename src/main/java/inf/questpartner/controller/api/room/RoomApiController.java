package inf.questpartner.controller.api.room;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.req.CreateRoomRequest;
import inf.questpartner.dto.room.res.ResRoomCreate;
import inf.questpartner.dto.room.res.ResRoomEnter;
import inf.questpartner.dto.room.res.ResRoomPreview;
import inf.questpartner.service.RoomService;
import inf.questpartner.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;
    private final UserService userService;

    // 방 생성
    @PostMapping("/new")
    public ResponseEntity<ResRoomCreate> createRoom(@RequestBody CreateRoomRequest form, @AuthenticationPrincipal User user) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드={}", currentThread);

        ResRoomCreate room = roomService.createRoom(form, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ResRoomPreview>> search(RoomSearchCondition condition, Pageable pageable) {

        Page<ResRoomPreview> result = roomService.sort(condition, pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ResRoomPreview>> roomList(Pageable pageable) {
        Page<ResRoomPreview> result = roomService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // username 회원이 roomId 방에 입장
    @PostMapping("/{roomId}/enter")
    public ResponseEntity<ResRoomEnter> enterRoom(@PathVariable(value = "roomId") Long id, @AuthenticationPrincipal User user) {
        ResRoomEnter dto = roomService.enterRoom(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // username 회원이 roomId 방에서 나가기
    @PostMapping("/{roomId}/exit/{username}")
    public ResponseEntity<Long> exitRoom(@PathVariable(value = "roomId") Long id, @AuthenticationPrincipal User user) {
        Room room = roomService.findById(id);

        User enterUser = userService.findByEmail(user);
        room.removeParticipant(enterUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/{roomId}")
    public ResponseEntity<Long> deleteRoom(@PathVariable("roomId") Long id, @AuthenticationPrincipal User user) {
        Room room = roomService.findById(id);

        // 삭제하기 전에, 방에 있는 회원 내보내기
        room.removeParticipantAll();
        // 삭제
        roomService.deleteRoom(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
