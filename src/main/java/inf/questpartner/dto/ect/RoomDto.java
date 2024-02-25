package inf.questpartner.dto.ect;

import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class RoomDto {

    private String roomName;
    private List<String> roomInterests;
    private List<TagOption> roomTags;
    private double matchingScore;


    public RoomDto(String name, List<String> roomInterests, List<TagOption> roomTags) {
        this.roomName = name;
        this.roomInterests = roomInterests;
        this.roomTags = roomTags;
    }


}
