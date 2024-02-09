package inf.questpartner.dto;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.UserWishHashTag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserWishTag {
    private int groupSize;
    private int expectedSchedule;
    private List<UserWishHashTag> userHashTags;

    @Builder
    public UserWishTag(int groupSize, int expectedSchedule, List<UserWishHashTag> userHashTags) {
        this.groupSize = groupSize;
        this.expectedSchedule = expectedSchedule;
        this.userHashTags = userHashTags;
    }
}
