package inf.questpartner.dto.chat;

import inf.questpartner.config.WebSocketConfig;
import inf.questpartner.domain.chat.ChattingRoom;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoomDto {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }


}
