package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.ChatBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatBoxRepository extends JpaRepository<ChatBox, Long> {
}
