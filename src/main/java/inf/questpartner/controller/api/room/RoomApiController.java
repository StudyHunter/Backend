package inf.questpartner.controller.api.room;

import inf.questpartner.controller.PageDto;
import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.controller.response.CreateRoomResponse;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.service.RoomService;

import inf.questpartner.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;

    // 방 생성
    @PostMapping("/new")
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomRequest form, @AuthenticationPrincipal User user) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드={}", currentThread);
        Room room = roomService.createRoom(form, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Room>> search(RoomSearchCondition condition, @PageableDefault(size=6) Pageable pageable) {

        Page<Room> result = roomService.sort(condition, pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // username 회원이 roomId 방에 입장
    @PostMapping("/{roomId}/enter")
    public ResponseEntity<Room> enterRoom(@PathVariable(value = "roomId") Long id, @AuthenticationPrincipal User user) {
        Room room = roomService.findById(id);
        room.addParticipant(user);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    // username 회원이 roomId 방에서 나가기
    @PostMapping("/{roomId}/exit/{username}")
    public ResponseEntity<Long> exitRoom(@PathVariable(value = "roomId") Long id, @AuthenticationPrincipal User user) {
        Room room = roomService.findById(id);
        room.removeParticipant(user);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{roomId}/studyEnd")
    public void endStudy(@PathVariable(value = "roomId") Long id, @RequestParam Integer time) {

    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Long> deleteRoom(@PathVariable("roomId") Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


/*
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

 */

}
