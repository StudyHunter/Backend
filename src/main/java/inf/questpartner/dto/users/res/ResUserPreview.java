package inf.questpartner.dto.users.res;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.domain.users.user.UserWishHashTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResUserPreview {

    private String nickname;
    private Integer studyTime;
    private List<String> wishTags;

    @Builder
    public ResUserPreview(String nickname, Integer studyTime, List<String> wishTags) {
        this.nickname = nickname;
        this.studyTime = studyTime;
        this.wishTags = wishTags;
    }

    public static ResUserPreview convertUser(User user) {
        return ResUserPreview.builder()
                .studyTime(user.getStudyTime())
                .nickname(user.getNickname())
                .wishTags(toUserTagOption(user.getUserHashTags()))
                .build();
    }

    private static List<String> toUserTagOption(List<UserWishHashTag> hashTags) {
        return hashTags.stream()
                .map(UserWishHashTag::getTagName)
                .collect(Collectors.toList());
    }

/*
    private static List<TagOption> toUserTagOption(List<UserWishHashTag> hashTags) {
        return hashTags.stream()
                .map(UserWishHashTag::getTagOption)
                .collect(Collectors.toList());
    }
 */
}
