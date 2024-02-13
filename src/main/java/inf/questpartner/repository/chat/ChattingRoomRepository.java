package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 예시
 */
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
