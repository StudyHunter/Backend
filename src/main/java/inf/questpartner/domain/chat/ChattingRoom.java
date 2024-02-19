package inf.questpartner.domain.chat;


import inf.questpartner.dto.chat.ChatRoomDto;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChattingRoom {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "chatting_room_id")
    private Long id;

    private String name;
    private String roomId;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<Chatting> chattingList = new ArrayList<>();

    @Builder
    public ChattingRoom(String name, String roomId) {
        this.name = name;
        this.roomId = roomId;
    }


    public ChatRoomDto toDto() {
        return ChatRoomDto.builder()
                .roomId(this.roomId)
                .name(this.name)
                .build();
    }
}