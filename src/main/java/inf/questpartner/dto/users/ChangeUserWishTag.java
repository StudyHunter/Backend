package inf.questpartner.dto.users;

import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ChangeUserWishTag {
    private String email;
    private int wishGroupSize;
    private int wishExpectedSchedule;
    private List<TagOption> tags;


    @Builder
    public ChangeUserWishTag(String email, int wishGroupSize,
                             int wishExpectedSchedule, List<TagOption> tags) {

        this.email = email;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.tags = tags;
    }
}
