package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.JoinChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinChatRepository extends JpaRepository<JoinChat, Long>, JoinChatRepositoryCustom {

}
