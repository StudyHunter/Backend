package inf.questpartner.service;

import inf.questpartner.util.exception.room.NotFoundRoomException;
import inf.questpartner.domain.room.Room;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;    //@RequiredArgsConstructor 어노테이션으로 생성자 주입

    @Transactional(readOnly = true)
    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Room findById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundRoomException("해당" + id + "번 방이 존재하지 않습니다."));
    }

    @Transactional
    public Room


    public Long createRoom(User user, CreateRoomRequest request){
        //요청 데이터를 받아 Room Entity를 만들어준다.
        //roomRepository.save(room)으로 Room을 저장한다.
        //User Entity에도 List 필드 값에 생성한 Room을 추가해준다.
        //Room ID를 반환해준다.

        Room room = request.toEntity(request.getAuthor());
        Long id = roomRepository.save(room).getId();

        user.createRoom(room);
        return id;
    }
}
