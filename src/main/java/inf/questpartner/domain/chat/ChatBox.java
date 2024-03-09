package inf.questpartner.domain.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import static jakarta.persistence.GenerationType.IDENTITY;
// chat box
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatBox {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "chat_box_id")
    private Long id;

    private String name;

    public ChatBox(String name) {
        this.name = name;
    }
}