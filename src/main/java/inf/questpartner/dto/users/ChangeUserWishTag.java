package inf.questpartner.dto.users;

import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChangeUserWishTag {
    private String email;
    private int wishGroupSize;
    private int wishExpectedSchedule;
    private List<TagOption> userHashTags;


    @Builder
    public ChangeUserWishTag(String email, int wishGroupSize,
                             int wishExpectedSchedule, List<TagOption> userHashTags) {

        this.email = email;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.userHashTags = userHashTags;
    }
}
