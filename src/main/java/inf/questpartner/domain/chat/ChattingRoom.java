package inf.questpartner.domain.chat;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChattingRoom {

    @Id
    @GeneratedValue
    @Column(name = "CHATTING_ROOM_ID")
    private Long id;

    private int roomNumber; // 방 번호
    private String roomName; // 방 번호


    // 1:1 단방향
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "CHATTING_ID")
    private Chatting chatting;
}
