package inf.questpartner.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long chatBoxId;
    private String sender;
    private String message;
    private LocalDateTime sendDate;


    public static ChatDto createDto(Long chatBoxId, String sender, String message) {
        return ChatDto.builder()
                .chatBoxId(chatBoxId)
                .sender(sender)
                .message(message)
                .sendDate(LocalDateTime.now())
                .build();
    }
}