package inf.questpartner.dto.users;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.user.User;
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
    private List<TagOption> userHashTags;
    private int wishGroupSize;
    private int wishExpectedSchedule;

    @Builder
    public UserInfoDto(User user, List<TagOption> userHashTags) {

        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.userLevel = user.getUserLevel();
        this.studyTime = user.getStudyTime();
        this.studyTree = user.getStudyTree();
        this.wishGroupSize = user.getWishGroupSize();
        this.wishExpectedSchedule = user.getWishExpectedSchedule();
        this.userHashTags = userHashTags;
    }
}
