package inf.questpartner.controller;

import inf.questpartner.domain.chat.ChatBox;
import inf.questpartner.dto.chat.ChatDto;
import inf.questpartner.service.ChatBoxService;
import inf.questpartner.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {


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
    public ResponseEntity<List<ChatDto>> joinRoom(@PathVariable(name = "chatBoxId", required = false) Long chatBoxId, @RequestParam("email") String email) {
        roomService.joinToChatRoom(email, chatBoxId);
        List<ChatDto> chatList = chatService.findAllByChatBoxId(chatBoxId);

      return ResponseEntity.status(HttpStatus.OK).body(chatList);
    }

    @GetMapping("/view/join")
    public String viewChatBox() {
        return "chat/roomin";
    }
}
