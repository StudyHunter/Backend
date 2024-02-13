package inf.questpartner.service;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.dto.RoomDto;
import inf.questpartner.dto.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class RoomRecommendServiceTest {

    @Autowired RoomRecommendService recommendService;

    @Test
    void test1() {
        UserProfile user = new UserProfile(List.of("프로그래밍", "머신러닝"), List.of(TagOption.PYTHON, TagOption.AI));
        List<RoomDto> allStudyRooms = List.of(
                new RoomDto("Study Room A", List.of("프로그래밍", "데이터과학"), List.of(TagOption.PYTHON, TagOption.DATA)),
                new RoomDto("Study Room B", List.of("머신러닝", "딥러닝"), List.of(TagOption.AI, TagOption.DEEPLEARNING)),
                new RoomDto("Study Room C", List.of("웹 개발", "자바스크립트"), List.of(TagOption.JAVASCRIPT, TagOption.TEAMPROJECT))
                                             );

        List<RoomDto> recommendedRooms = recommendService.recommend(user, allStudyRooms);

        // Display recommended rooms to the user
        for (RoomDto room : recommendedRooms) {
            System.out.println(room.getRoomName() + " - Matching Score: " + room.getMatchingScore());
        }
    }


}