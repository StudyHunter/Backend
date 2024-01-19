package inf.questpartner.domain.chat;

import inf.questpartner.domain.room.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chatting {

    @Id
    @GeneratedValue
    @Column(name = "CHATTING_ID")
    private Long id;

    private String message;


    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

}
