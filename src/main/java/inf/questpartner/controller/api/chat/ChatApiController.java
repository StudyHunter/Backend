package inf.questpartner.controller.api.chat;


import inf.questpartner.config.login.jwt.JwtProperties;
import inf.questpartner.controller.dto.ChatDto;
import inf.questpartner.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatApiController {
    private final ChatService chatService;
    private final JwtProperties jwtProperties;

    @MessageMapping("/{roomId}")
    @SendTo("/room/{roomId}") // 여길 구독하고 있는 곳으로 메시지 전송
    public ChatDto messageHandler(@DestinationVariable("roomId") Long roomId, ChatDto message, @Header("token") String token) {
        String loginId = jwtProperties.getUsernameFromToken(token);//loginId 가져옴
        return chatService.createChat(roomId, message.getMessage(), loginId);
    }

//    @GetMapping("/chat/chatList")
//    public String chatList(Model model){
//        List<ChatRoomDto> roomList = chatService.findAllRoom();
//        model.addAttribute("roomList",roomList);
//        return "chat/chatList";
//    }
//
//    @PostMapping("/chat/createRoom")  //방을 만들었으면 해당 방으로 이동된다.
//    public String createRoom(@RequestParam("name") String name, Model model) {
//
//        ChattingRoom room = chatService.createRoom(name);
//        model.addAttribute("room", room.toDto());
//
//        return "chat/chatRoom";  //만든사람이 채팅방 먼저 들어간다
//    }
//
//    @GetMapping("/chat/chatRoom")
//    public String chatRoom(@RequestParam("roomId") String roomId, Model model) {
//        ChatRoomDto room = chatService.findRoomById(roomId);
//        model.addAttribute("room",room);
//        return "chat/chatRoom";
//    }


}