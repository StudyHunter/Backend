package inf.questpartner.dto.room.req;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Data;
import java.util.List;


@Data
public class CreateRoomRequest {

    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한

    private RoomThumbnail thumbnail; // 방 섬네일
    private List<TagOption> tags; // 방 태그표시들

    public Room toRoomEntity(String hostEmail) {
        return Room.createRoom()
                .hostEmail(hostEmail)
                .title(title)
                .expectedUsers(expectedUsers)
                .thumbnail(thumbnail)
                .build();
    }
}
