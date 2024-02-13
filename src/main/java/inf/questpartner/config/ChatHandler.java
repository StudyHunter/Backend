package inf.questpartner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import inf.questpartner.domain.chat.Chatting;
import inf.questpartner.domain.chat.MessageType;
import inf.questpartner.dto.chat.ChatRoomDto;
import inf.questpartner.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Chatting chatting = objectMapper.readValue(payload, Chatting.class);
        ChatRoomDto dto = chatService.findRoomById(chatting.getRoomId());
        Set<WebSocketSession> sessions = dto.getSessions();

        if (chatting.getType().equals(MessageType.JOIN)) {
            sessions.add(session);
            chatting.enterContent(chatting.getSender() + "님이 입장했습니다.");
            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatting)));
        } else if (chatting.getType().equals(MessageType.LEAVE)) {
            sessions.remove(session);
            chatting.enterContent(chatting.getSender() + "님이 퇴장했습니다.");
            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatting)));
        } else {
            sendToEachSocket(sessions, message);
        }
    }

    private  void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message){
        sessions.parallelStream().forEach( roomSession -> {
            try {
                roomSession.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");

    }
}