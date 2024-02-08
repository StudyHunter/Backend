package inf.questpartner.dto.chat;

import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.domain.users.user.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ChatRoomDto {

    private Long roomId;
    private String name;
    private Long userId;

    /*
    public static List<ChatRoomDto> createList(List<ChattingRoom> list) {
        return list.stream()
                .map(chatRoom -> ChatRoomDto.builder()
                        .roomId(chatRoom.getId())
                        .name(chatRoom.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public ChattingRoom of(User user) {
        return ChattingRoom.builder().name(name).user(user).build();
    }
     */
}
