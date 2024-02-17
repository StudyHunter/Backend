package inf.questpartner.repository.users;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.domain.users.user.UserWishHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHashTagRepository extends JpaRepository<UserWishHashTag, Long> {

    List<UserWishHashTag> findByUser(User uSer);
}
