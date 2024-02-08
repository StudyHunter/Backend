package inf.questpartner.controller.api.chat;

import inf.questpartner.controller.response.ChatResponse;
import inf.questpartner.dto.chat.ChatRoomDto;
import inf.questpartner.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.core.Authentication;
@RestController
@RequestMapping("/api/v1/chatroom")
@RequiredArgsConstructor
public class ChatRoomApiController {

    private final ChattingRoomService chattingRoomService;
/*
    @PostMapping
    public ChatResponse addRoom(@RequestBody ChatRoomDto chatRoomDto, @RequestParam String userName) {
        chattingRoomServiceo.createChatRoomDto(chatRoomDto,userName);
        return ChatResponse.success("성공적 빌드");
    }

 */


}
