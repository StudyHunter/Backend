package inf.questpartner.dto.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResRoomPreview {

    private Long roomId;

    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private List<TagOption> roomHashTags;
    private int currentUsers;

    @Builder
    public ResRoomPreview(Long roomId, String hostEmail, String title, int expectedUsers, List<TagOption> roomHashTags, int currentUsers) {
        this.roomId = roomId;
        this.hostEmail = hostEmail;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomHashTags = roomHashTags;
        this.currentUsers = currentUsers;
    }

    public static ResRoomPreview fromEntity(Room room) {

        return ResRoomPreview.builder()
                .roomId(room.getId())
                .hostEmail(room.getHostEmail())
                .title(room.getTitle())
                .expectedUsers(room.getExpectedUsers())
                .roomHashTags(toTagOption(room.getRoomHashTags()))
                .currentUsers(toUserNum(room.getParticipants()))
                .build();
    }

    private static List<TagOption> toTagOption(List<RoomHashTag> hashTags) {
        return hashTags.stream()
                .map(RoomHashTag::getTagOption)
                .collect(Collectors.toList());
    }

    private static int toUserNum(List<User> users) {
        return users.size();
    }


    public static Page<ResRoomPreview> convert(Page<Room> roomPage) {
        return roomPage.map(ResRoomPreview::fromEntity);
    }


}
