package inf.questpartner.domain.room.common.tag;

import java.util.Arrays;

public enum RoomTagGroup {



    INTERESTS("관심분야", new TagOption[]{
            TagOption.AI, TagOption.DEEP_LEARNING, TagOption.CODING_TEST, TagOption.ALGORITHM, TagOption.DEVELOP, TagOption.INTERVIEW, TagOption.STARTUP}),
    SKILL("기술", new TagOption[]{
            TagOption.PYTHON, TagOption.JAVA, TagOption.JAVASCRIPT, TagOption.NODEJS, TagOption.REACT, TagOption.SPRING}),
    STUDYTYPE("공부유형", new TagOption[]{
       TagOption.BOOK_REVIEW, TagOption.TEAM_PROJECT, TagOption.CODE_REVIEW}),

    EMPTY("없음", new TagOption[]{});

    private String viewName;
    private TagOption[] containTag;

    RoomTagGroup(String viewName, TagOption[] containTag) {
        this.viewName = viewName;
        this.containTag = containTag;
    }

    public static RoomTagGroup findGroup(TagOption searchTag) {
        return Arrays.stream(RoomTagGroup.values())
                .filter(group -> hasTagOption(group, searchTag))
                .findAny()
                .orElse(RoomTagGroup.EMPTY);
    }

    private static boolean hasTagOption(RoomTagGroup from, TagOption searchTag) {
        return Arrays.stream(from.containTag)
                .anyMatch(containTag -> containTag == searchTag);
    }

    public String getViewName() {
        return viewName;
    }
}
