package inf.questpartner.dto.room.res;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;
import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResRoomUpdate {
    private Long roomId;
    private String thumbnailPath;
    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private List<TagOption> roomHashTags;

    @Builder
    public ResRoomUpdate(Long roomId, String thumbnailPath, String hostEmail, String title, int expectedUsers, List<TagOption> tags) {
        this.roomId = roomId;
        this.thumbnailPath = thumbnailPath;
        this.hostEmail = hostEmail;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomHashTags = tags;
    }

    public static ResRoomUpdate fromEntity(Room room) {

        return ResRoomUpdate.builder()
                .roomId(room.getId())
                .thumbnailPath(room.getThumbnail().getImgPath())
                .hostEmail(room.getHostEmail())
                .title(room.getTitle())
                .expectedUsers(room.getExpectedUsers())
                .tags(toTagOption(room.getRoomHashTags()))
                .build();
    }

    private static List<TagOption> toTagOption(Set<RoomHashTag> hashTags) {
        return hashTags.stream()
                .map(RoomHashTag::getTagOption)
                .collect(Collectors.toList());
    }


}