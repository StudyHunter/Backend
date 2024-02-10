package inf.questpartner.dto.users;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.user.UserWishHashTag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {

    private String email;
    private String nickname;
    private UserLevel userLevel;

    private int studyTime;
    private StudyTree studyTree;
    private List<UserWishHashTag> userHashTags;
    private int wishGroupSize;
    private int wishExpectedSchedule;

    @Builder
    public UserInfoDto(String email, String nickname, UserLevel userLevel,
                       int studyTime, StudyTree studyTree, int wishGroupSize,
                       int wishExpectedSchedule, List<UserWishHashTag> userHashTags) {

        this.email = email;
        this.nickname = nickname;
        this.userLevel = userLevel;
        this.studyTime = studyTime;
        this.studyTree = studyTree;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.userHashTags = userHashTags;
    }
}
