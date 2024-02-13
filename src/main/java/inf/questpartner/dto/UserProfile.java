package inf.questpartner.dto;

import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Data;

import java.util.List;

@Data
public class UserProfile {
    private List<String> interests;
    private List<TagOption> wishTags;

    public UserProfile(List<String> interests, List<TagOption> wishTags) {
        this.interests = interests;
        this.wishTags = wishTags;
    }
}
