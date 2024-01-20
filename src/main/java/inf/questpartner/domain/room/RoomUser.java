package inf.questpartner.domain.room;

import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoomUser {
    @Id
    @GeneratedValue
    @Column(name = "ROOM_USER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public RoomUser(Room room, User user) {
        this.room = room;
        this.user = user;
    }
}
