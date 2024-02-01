package inf.questpartner.controller;

import inf.questpartner.dto.chat.ChatRoom;
import inf.questpartner.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam(value = "name") String name) {
        return chatService.createRoom(name);
    }

    /*
    @GetMapping
    public List<ChatRoom> findAllRooms() {
        return chatService.findAllRoom();
    }

     */

    @GetMapping
    public String chatGET(){

        log.info("@ChatController, chat GET()");

        return "chat";
    }

}
