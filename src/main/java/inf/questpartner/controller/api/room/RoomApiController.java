package inf.questpartner.controller.api.room;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.*;
import inf.questpartner.dto.room.req.CreateRoomRequest;
import inf.questpartner.dto.room.req.UpdateRoomRequest;
import inf.questpartner.dto.room.res.ResRoomCreate;
import inf.questpartner.dto.room.res.ResRoomEnter;
import inf.questpartner.dto.room.res.ResRoomPreview;
import inf.questpartner.dto.room.res.ResRoomUpdate;
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
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //방 수정
    @PatchMapping("/{roomId}")
    public ResponseEntity<ResRoomUpdate> updateForm(@PathVariable(value = "roomId") Long roomId,
                                                    @RequestBody UpdateRoomRequest form,
                                                    @AuthenticationPrincipal User user) {

//        Thread currentThread = Thread.currentThread();
//        log.info("현재 실행 중인 스레드={}", currentThread);

        ResRoomUpdate room = roomService.updateRoom(roomId, form, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    // username 회원이 roomId 방에 입장
    @PostMapping("/{roomId}/enter")
    public ResponseEntity<ResRoomEnter> enterRoom(@PathVariable(value = "roomId") Long roomId, @AuthenticationPrincipal User user) {
        ResRoomEnter dto = roomService.enterRoom(roomId, user);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // username 회원이 roomId 방에서 나가기
    @PostMapping("/{roomId}/exit")
    public ResponseEntity<Long> exitRoom(@PathVariable(value = "roomId") Long id, @AuthenticationPrincipal User user) {
        Room room = roomService.findById(id);

        User enterUser = userService.findByEmail(user.getEmail());
        room.removeParticipant(enterUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //    스터디 타이머 시작
    @PostMapping("/{roomId}/startStudy")
    public ResponseEntity<StartTimeDto> startStudy(@PathVariable(value = "roomId") Long id) {
        StartTimeDto startTimeDto = roomService.startStudy(id);
        return ResponseEntity.status(HttpStatus.OK).body(startTimeDto);
    }

    //    스터디 타이머 종료
    @PostMapping("/{roomId}/endStudy")
    public ResponseEntity<StudyTokenDto> endStudy(@PathVariable(value = "roomId") Long id) {
        StudyTokenDto studyTokenDto = roomService.endStudy(id);
        return ResponseEntity.status(HttpStatus.OK).body(studyTokenDto);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Long> deleteRoom(@PathVariable("roomId") Long id, @AuthenticationPrincipal User user) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //방 목록 조회
//    추가로 ResponseEntity 작업 필요함
    //메인화면에서 방 목록과 다른 파트도 보이겠지만 일단은 rooms/list에서 방 목록 따로 조회하는걸로 해놓았어요!
//    @GetMapping("/list")
//    public List<Room> roomList(){
//        /*List<Room> roomList = roomService.findByAll();
//        return "room/roomList";*/
//        return roomService.findByAll();
//    }

/*
    // 데이터 기반으로 추천 받기
    @GetMapping("/recommend")
    public List<RoomTag> recommendRooms(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        List<RoomTag> roomTags = roomService.findRoomTags();

        return roomService.recommendLogic(user.toUserWishDto(), roomTags);
    }
 */

}
