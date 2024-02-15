package inf.questpartner.dto;

import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class RoomTag {
    private int groupSize;
    private int expectedSchedule;
    private List<TagOption> tagList;
    private double matchingScore;

    @Builder

    public RoomTag(int groupSize, int expectedSchedule, List<TagOption> tagList) {
        this.groupSize = groupSize;
        this.expectedSchedule = expectedSchedule;
        this.tagList = tagList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RoomTag roomTag = (RoomTag) obj;
        return groupSize == roomTag.groupSize &&
                expectedSchedule == roomTag.expectedSchedule &&
                Objects.equals(tagList, roomTag.tagList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupSize, expectedSchedule, tagList);
    }
}
