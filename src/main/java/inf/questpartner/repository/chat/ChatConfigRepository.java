package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.ChatConfigEntity;
import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.domain.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatConfigRepository extends JpaRepository<ChatConfigEntity, Long> {
    Optional<ChatConfigEntity> findByUserAndChattingRoom(User user, ChattingRoom chatRoom);
    Boolean existsByUserAndChattingRoom(User user, ChattingRoom chatRoom);
}
