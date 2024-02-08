package inf.questpartner.repository.users;

import inf.questpartner.domain.users.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    User findByNickname(String nickname);

    boolean existsByEmailAndPassword(String email, String password);

    void deleteByEmail(String email);

}
