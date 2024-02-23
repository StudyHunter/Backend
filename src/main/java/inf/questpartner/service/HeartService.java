package inf.questpartner.service;

import inf.questpartner.domain.room.Heart;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.repository.heart.HeartRepository;
import inf.questpartner.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class HeartService {
    private final RoomService roomService;
    private final HeartRepository heartRepository;

    @Transactional
    public void addHeart(Long roomId, User user){
        Room room = roomService.findById(roomId);

        if (!heartRepository.existsByUserAndRoom(user, room)){
         //호출되면 room에 있는 heartCount 증가
          room.increaseHeartCount();
          //heartRepository에 userId 값과 roomId 값 저장
          heartRepository.save(new Heart(user, room));
        }
        else {
            //호출되면 room에 있는 heartCount 감소
            room.decreaseHeartCount();
            heartRepository.deleteByUserAndRoom(user, room);
        }
    }
}
