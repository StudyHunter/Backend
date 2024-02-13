package inf.questpartner.repository.like;

import inf.questpartner.domain.like.Like;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndRoom(User user, Room room);

}
