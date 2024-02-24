package inf.questpartner.controller.api.room;

import inf.questpartner.controller.PageDto;
import inf.questpartner.controller.dto.CurrentUser;
import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.controller.response.CreateRoomResponse;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.dto.room.ResRoomEnter;
import inf.questpartner.dto.room.StartTimeDto;
import inf.questpartner.dto.room.StudyTokenDto;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.service.RoomService;

import inf.questpartner.service.UserService;
import inf.questpartner.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;
    private final UserService userService;
    private final UserRepository userRepository;

    // 방 생성
    @PostMapping("/new")
    public ResponseEntity<Room> createRoom(@RequestBody CreateRoomRequest form, UserDetails userDetails) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드={}", currentThread);

        Room room = roomService.createRoom(form, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Room>> search(RoomSearchCondition condition, @PageableDefault(size = 6) Pageable pageable) {

        Page<Room> result = roomService.sort(condition, pageable);
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
    public ResponseEntity<Long> exitRoom(@PathVariable(value = "roomId") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Room room = roomService.findById(id);

        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        room.removeParticipant(user);

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
    public ResponseEntity<Long> deleteRoom(@PathVariable("roomId") Long id, @AuthenticationPrincipal UserDetails userDetails) {
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
