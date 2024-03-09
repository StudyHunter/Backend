package inf.questpartner.controller;

import inf.questpartner.domain.chat.ChatBox;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.chat.ChatDto;
import inf.questpartner.service.ChatBoxService;
import inf.questpartner.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@Controller
//@RequiredArgsConstructor
public class IndexController {

    /*
    private final ChatBoxService roomService;
    private final ChatService chatService;

    @GetMapping("/view/login")
    public String loginForm() {
        return "chat/login";
    }

    @GetMapping("/roomList")
    public String roomList(Model model) {
        List<ChatBox> roomList = roomService.findAll();
        model.addAttribute("roomList", roomList);
        return "chat/roomList";
    }

    @GetMapping("/view/{chatBoxId}")
    public String joinRoom(@PathVariable(name = "chatBoxId", required = false) Long chatBoxId, Model model, @AuthenticationPrincipal User user) {
        roomService.joinToChatRoom(user, chatBoxId);
        List<ChatDto> chatList = chatService.findAllByChatBoxId(chatBoxId);

        model.addAttribute("chatBoxId", chatBoxId);
        model.addAttribute("chatList", chatList);
        return "chat/roomIn";
    }

     */
}
