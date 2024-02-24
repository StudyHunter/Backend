package inf.questpartner.service;

import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.dto.room.ResRoomCreate;
import inf.questpartner.dto.room.ResRoomEnter;
import inf.questpartner.repository.room.RoomHashTagRepository;
import inf.questpartner.repository.room.RoomRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.ResourceNotFoundException;
import inf.questpartner.util.exception.room.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@Transactional
@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomHashTagRepository hashTagRepository;
    private final UserRepository userRepository;
    public ResRoomCreate createRoom(CreateRoomRequest req, User user) {
        User enterUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        Room room = roomRepository.save(req.toRoomEntity(enterUser.getEmail()));
        room.createChatRoom(new ChattingRoom());

        for (TagOption tag : req.getTags()) {
            RoomHashTag hashTag = hashTagRepository.save(new RoomHashTag(room, tag));
            room.addHashTag(hashTag);
        }
        return ResRoomCreate.fromEntity(room);
    }

    @Transactional(readOnly = true)
    public ResRoomEnter enterRoom(Long id, User user) {
        User enterUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        Room room = findById(id);
        room.addParticipant(enterUser);

        for (User participant : room.getParticipants()) {
            log.info("participant nickname = {}", participant.getNickname());
        }
        return ResRoomEnter.fromEntity(room);
    }


    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundRoomException("존재하지 않는 방입니다."));
    }

    public void deleteRoom(Long id) {
        Room room = findById(id);
        roomRepository.delete(room);
    }

    public void endStudy(Long id, int time) {
        Room room = findById(id);
        room.studyEnd(time);
        room.getParticipants().forEach(participant -> participant.updateTotalTime(time));
    }

    @Transactional(readOnly = true)
    public Page<Room> sort(RoomSearchCondition condition, Pageable pageable) {
        return roomRepository.searchPageSort(condition, pageable);
    }

    /*
    // 취향 방 추천
    @Transactional(readOnly = true)
    public List<RoomTag> recommendLogic(UserWishTag user, List<RoomTag> roomTags) {
        for (RoomTag roomTag : roomTags) {
            double matchingScore = calculateMatchingScore(user, roomTag);
            roomTag.setMatchingScore(matchingScore);
        }

        List<RoomTag> modifiableRoomTags = new ArrayList<>(roomTags);
        modifiableRoomTags.sort(Comparator.comparingDouble(RoomTag::getMatchingScore).reversed());

        return Collections.unmodifiableList(modifiableRoomTags);
    }

    private static double calculateMatchingScore(UserWishTag userWishTag, RoomTag roomTag) {
        // 예상 기간 매칭 점수 계산
        double scheduleScore = calculateScheduleScore(userWishTag.getExpectedSchedule(), roomTag.getExpectedSchedule());

        // 방 태그 매칭 점수 계산
        double tagScore = calculateTagScore(userWishTag.getTagList(), roomTag.getTagList());

        // 종합적인 매칭 점수 반환 (가중치 적용 가능)
        return (scheduleScore + tagScore) / 2;
    }

    private static double calculateScheduleScore(int userExpectedSchedule, int roomExpectedSchedule) {
        // 예상 기간이 얼마나 일치하는지 계산
        int difference = Math.abs(userExpectedSchedule - roomExpectedSchedule);
        return 1.0 / (1 + difference); // 간단한 매칭 점수 계산 방법
    }

    private static double calculateTagScore(List<TagOption> userTags, List<TagOption> roomTags) {
        // 두 리스트에서 공통으로 포함된 태그의 개수 계산
        long commonTagsCount = userTags.stream()
                .filter(roomTags::contains)
                .count();

        // 두 리스트 중 크기가 더 작은 쪽의 태그 개수를 기준으로 점수 계산
        int smallerListSize = Math.min(userTags.size(), roomTags.size());
        return (double) commonTagsCount / smallerListSize;
    }

     */

}