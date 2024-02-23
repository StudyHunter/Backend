package inf.questpartner.repository.heart;

import inf.questpartner.domain.room.Heart;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    //존재 여부 검토
    boolean existsByUserAndRoom(User user, Room room);
    //삭제
    void deleteByUserAndRoom(User user, Room room);
}
