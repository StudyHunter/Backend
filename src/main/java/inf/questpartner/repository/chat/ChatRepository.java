package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 예시
 */
public interface ChatRepository extends JpaRepository<Chatting, Long> {
}
