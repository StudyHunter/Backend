package inf.questpartner.config.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import inf.questpartner.config.login.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatHandler implements ChannelInterceptor {

    private final JwtProperties jwtProperties;
    private static final String BEARER_PREFIX = "Bearer ";

    // websocket을 통해 들어온 요청이 처리 되기전 실행된다.
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT == accessor.getCommand()) { // websocket 연결요청
            String jwtToken = accessor.getFirstNativeHeader("Authorization");
            log.info("CONNECT {}", jwtToken);
            // Header의 jwt token 검증
            String token = jwtToken.substring(7);
            jwtProperties.validateTokenInChat(token);
        }

        return message;
    }


//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        Chatting chatting = objectMapper.readValue(payload, Chatting.class);
//        ChatRoomDto dto = chatService.findRoomById(chatting.getRoomId());
//        Set<WebSocketSession> sessions = dto.getSessions();
//
//        if (chatting.getType().equals(MessageType.JOIN)) {
//            sessions.add(session);
//            chatting.enterContent(chatting.getSender() + "님이 입장했습니다.");
//            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatting)));
//        } else if (chatting.getType().equals(MessageType.LEAVE)) {
//            sessions.remove(session);
//            chatting.enterContent(chatting.getSender() + "님이 퇴장했습니다.");
//            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatting)));
//        } else {
//            sendToEachSocket(sessions, message);
//        }
//    }
//
//    private  void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message){
//        sessions.parallelStream().forEach( roomSession -> {
//            try {
//                roomSession.sendMessage(message);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    /* Client가 접속 시 호출되는 메서드 */
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info(session + " 클라이언트 접속");
//    }
//
//    /* Client가 접속 해제 시 호출되는 메서드드 */
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info(session + " 클라이언트 접속 해제");
//
//    }
}