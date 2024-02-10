package inf.questpartner.dto.users;

import inf.questpartner.domain.users.user.UserWishHashTag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ChangeUserWishTag {
    private String email;
    private int wishGroupSize;
    private int wishExpectedSchedule;
    private List<UserWishHashTag> userHashTags;


    @Builder
    public ChangeUserWishTag(String email, int wishGroupSize,
                             int wishExpectedSchedule, List<UserWishHashTag> userHashTags) {

        this.email = email;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.userHashTags = userHashTags;
    }
}
