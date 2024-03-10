package inf.questpartner.repository.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom {
/*

    @Query("SELECT DISTINCT r FROM Room r " +
            "LEFT JOIN FETCH r.roomHashTags t " +
            "LEFT JOIN FETCH r.participants u")
    Page<Room> findAllWithTagAndUser(Pageable pageable);



    @Query("SELECT DISTINCT r FROM Room r " +
            "LEFT JOIN FETCH r.roomHashTags t " +
            "LEFT JOIN FETCH r.participants u " +
            "WHERE t.tagOption = :tagOption")
    Page<Room> findByTagOption(TagOption tagOption, Pageable pageable);
}


 */
}