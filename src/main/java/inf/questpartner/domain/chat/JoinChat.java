package inf.questpartner.domain.chat;


import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinChat {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "join_chat_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_box_id")
    private ChatBox chatBox;

}