### RoomController
```java
package com.example.chat.controller;

import com.example.chat.dto.room.CreateRoomRequest;
import com.example.chat.model.room.Room;
import com.example.chat.model.room.common.RoomThumbnail;
import com.example.chat.model.room.common.RoomType;
import com.example.chat.model.room.common.tag.TagOption;
import com.example.chat.model.users.User;
import com.example.chat.service.RoomService;
import com.example.chat.service.UserService;
import com.example.chat.util.consts.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

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

     // 등록한 방을 id로 조회
    @GetMapping("/{roomId}")
    public String roomDetail(@PathVariable Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("hashTags", room.getRoomHashTags());

        return "room/roomDetail";
    }

}
```
