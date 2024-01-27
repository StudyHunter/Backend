package inf.questpartner.dto.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class CreateRoomRequest {

    private String author;
    private List<TagOption> tags;
    private RoomType roomType;


    public CreateRoomRequest(String user, List<TagOption> option, RoomType roomType) {
        this.author = user;
        this.tags = option;
        this.roomType = roomType;
    }


    public Room roomEntity() {
        return Room.builder()
                .author(author)
                .tags(tags)
                .roomType(roomType)
                .build();
    }
}
