package inf.questpartner.controller.api;


import inf.questpartner.domain.chat.Chatting;
import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.dto.chat.ChatRoomDto;
import inf.questpartner.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ChatApiController {

    private final ChatService chatService;

    @GetMapping("/chat/chatList")
    public String chatList(Model model){
        List<ChatRoomDto> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    @PostMapping("/chat/createRoom")  //방을 만들었으면 해당 방으로 이동된다.
    public String createRoom(@RequestParam("name") String name, @RequestParam(required = false, name = "username") String username, Model model) {
        ChattingRoom room = chatService.createRoom(name);
        model.addAttribute("room", room.toDto());
        model.addAttribute("username", username);
        return "chat/chatRoom";  //만든사람이 채팅방 먼저 들어간다
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(@RequestParam("roomId") String roomId, Model model) {
        ChatRoomDto room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);
        return "chat/chatRoom";
    }






}