package inf.questpartner.repository.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
