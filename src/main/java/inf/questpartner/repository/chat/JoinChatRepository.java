package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.JoinChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoinChatRepository extends JpaRepository<JoinChat, Long> {

    @Query("select j from JoinChat j join fetch j.room r where j.user.email = :email")
    List<JoinChat> findByUserId(@Param("email") String email);

    @Query("select j from JoinChat j where j.user.email = :email and j.room.id = :roomId")
    Optional<JoinChat> findByUserIdAndRoomId(@Param("email")String email, @Param("roomId")Long roomId);
}
