package inf.questpartner.repository.users;

import inf.questpartner.domain.users.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByEmailAndPassword(String email, String password);

    void deleteByEmail(String email);

    User findByNickname(String nickname);
}
