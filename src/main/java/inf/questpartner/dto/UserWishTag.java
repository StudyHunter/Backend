package inf.questpartner.dto;

import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class UserWishTag {
    private int groupSize;
    private int expectedSchedule;
    private List<TagOption> tagList;

    public UserWishTag(int groupSize, int expectedSchedule, List<TagOption> tagList) {
        this.groupSize = groupSize;
        this.expectedSchedule = expectedSchedule;
        this.tagList = tagList;
    }


}
