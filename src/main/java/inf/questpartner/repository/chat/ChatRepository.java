package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByChatBoxId(Long chatBoxId);
}