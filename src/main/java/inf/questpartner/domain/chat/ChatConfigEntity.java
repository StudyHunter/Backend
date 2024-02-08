package inf.questpartner.domain.chat;

import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatConfigEntity {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime configTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private ChattingRoom chattingRoom;

    public void setTime() {
        this.configTime = LocalDateTime.now();
    }
}
