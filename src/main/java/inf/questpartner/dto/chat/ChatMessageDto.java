package inf.questpartner.dto.chat;

import inf.questpartner.domain.chat.Chatting;
import inf.questpartner.domain.chat.ChattingRoom;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private Long roomId;
    private String writer;
    private String message;

    private String createdAt;
    private List<String> userList;
    private Integer status;
/*
    public Chatting toChat(ChattingRoom chatRoom) {
        return Chatting.builder()
                .message(message)
                .chattingRoom(chatRoom)
                .isChecked(false)
                .build();
    }
 */
}
