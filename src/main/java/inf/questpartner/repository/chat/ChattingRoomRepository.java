package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
