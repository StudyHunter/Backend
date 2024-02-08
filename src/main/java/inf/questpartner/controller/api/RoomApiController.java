package inf.questpartner.controller.api;

import inf.questpartner.controller.response.CreateRoomResponse;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.service.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;


    // 방 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("tags", TagOption.values());
        model.addAttribute("thumbnails", RoomThumbnail.values());
        model.addAttribute("roomTypes", RoomType.values());
        model.addAttribute("form", new CreateRoomRequest());

        return "room/createRoomForm";
    }


    // 등록 이벤트
    @PostMapping("/new")
    public String createRoom(@ModelAttribute CreateRoomRequest form, RedirectAttributes redirectAttributes) {
        Long id = roomService.createRoom(form);
        redirectAttributes.addAttribute("roomId", id);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/rooms/{roomId}";
    }

    @GetMapping("/{roomId}")
    public String roomDetail(@PathVariable Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("hashTags", room.getRoomHashTags());

        return "room/roomDetail";
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
