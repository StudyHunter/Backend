package inf.questpartner.repository.room;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RoomRepositoryCustom {

    Page<Room> searchPageSort(RoomSearchCondition condition, Pageable pageable);
}