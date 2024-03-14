package inf.questpartner.controller.api.chat;

import inf.questpartner.config.login.jwt.JwtProperties;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.chat.ChatDto;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.service.ChatService;
import inf.questpartner.service.UserService;
import inf.questpartner.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;
    @MessageMapping("/{chatBoxId}")
    @SendTo("/room/{chatBoxId}") // 여길 구독하고 있는 곳으로 메시지 전송
    public ChatDto messageHandler(@DestinationVariable("chatBoxId") Long roomId, ChatDto message,  @Header("Authorization") String token) {
        log.info("chat controller -> token check={}", token);
        String email = jwtProperties.getEmailFromJwt(token);
        log.info("chat controller jwt check --> email {}", email);

        User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "User Email", email));

        return chatService.createChat(roomId, message.getMessage(), currentUser.getNickname());
    }

    @GetMapping("/{chatBoxId}")
    public ResponseEntity<List<ChatDto>> findAllChat(@PathVariable(name = "chatBoxId", required = false) Long chatBoxId) {
        List<ChatDto> chatList = chatService.findAllByChatBoxId(chatBoxId);

        return ResponseEntity.status(HttpStatus.OK).body(chatList);
    }
}
