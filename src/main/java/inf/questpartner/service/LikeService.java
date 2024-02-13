package inf.questpartner.service;

import inf.questpartner.domain.like.Like;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.like.LikeRequest;
import inf.questpartner.repository.like.LikeRepository;
import inf.questpartner.repository.room.RoomRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.like.NotFoundLikeException;
import inf.questpartner.util.exception.room.NotFoundRoomException;
import inf.questpartner.util.exception.users.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public void insert(LikeRequest likeRequest) throws Exception {

        User user = userRepository.findById(likeRequest.getUserId())
                .orElseThrow(() -> new NotFoundUserException("Could not found user id : " + likeRequest.getUserId()));
        Room room = roomRepository.findById(likeRequest.getRoomId())
                .orElseThrow(() -> new NotFoundRoomException("Could not found room id : " + likeRequest.getRoomId()));

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByUserAndRoom(user, room).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        Like like = Like.builder()
                .room(room)
                .user(user)
                .build();

        likeRepository.save(like);

        // 좋아요를 누르면 count + 1
        roomRepository.updateCount(room, true);
    }

    @Transactional
    public void delete(LikeRequest likeRequest) {

        User user = userRepository.findById(likeRequest.getUserId())
                .orElseThrow(() -> new NotFoundUserException("Could not found user id : " + likeRequest.getUserId()));

        Room room = roomRepository.findById(likeRequest.getRoomId())
                .orElseThrow(() -> new NotFoundRoomException("Could not found room id : " + likeRequest.getRoomId()));

        Like like = likeRepository.findByUserAndRoom(user, room)
                .orElseThrow(() -> new NotFoundLikeException("Could not found like id"));

        likeRepository.delete(like);

        // 좋아요 취소를 누르면 count - 1
        roomRepository.updateCount(room, false);
    }

}
