package inf.questpartner.service;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.dto.RoomDto;
import inf.questpartner.dto.UserProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class RoomRecommendService {

    public static List<RoomDto> recommend(UserProfile user, List<RoomDto> rooms) {
        List<RoomDto> recommendedRooms = new ArrayList<>();

        for (RoomDto room : rooms) {
            double matchingScore = calculateMatchingScore(user, room);
            room.setMatchingScore(matchingScore);
        }

        List<RoomDto> mutableRooms = new ArrayList<>(rooms);
        mutableRooms.sort(Comparator.comparingDouble(RoomDto::getMatchingScore).reversed());


        for (RoomDto room : rooms) {
            recommendedRooms.add(room);
        }

        return recommendedRooms;
    }

    private static double calculateMatchingScore(UserProfile user, RoomDto room) {
        double interestScore = calculateInterestScore(user, room);  // 메서드 이름 수정
        double tagScore = calculateTagScore(user, room);

        return interestScore + tagScore;
    }


    private static double calculateInterestScore(UserProfile user, RoomDto room) {
        long commonInterestsCount = user.getInterests().stream()
                .filter(room.getRoomInterests()::contains)
                .count();

        return (double) commonInterestsCount / user.getInterests().size();
    }

    private static double calculateTagScore(UserProfile user, RoomDto room) {
        long commonTagsCount = user.getWishTags().stream()
                .filter(room.getRoomTags()::contains)
                .count();
        return (double) commonTagsCount / user.getWishTags().size();
    }

}
