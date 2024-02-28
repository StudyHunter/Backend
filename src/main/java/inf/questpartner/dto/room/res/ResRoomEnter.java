package inf.questpartner.dto.room.res;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.res.ResUserPreview;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResRoomEnter {
    private Long roomId;
    private String thumbnailPath;
    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int currentUsers;
    private int expectedUsers; // 인원수 제한
    private List<String> roomHashTags;
    private List<ResUserPreview> users;

    @Builder
    public ResRoomEnter(Long roomId, String thumbnailPath, String hostEmail, String title, int currentUsers, int expectedUsers, List<String> tags, List<ResUserPreview> users) {
        this.roomId = roomId;
        this.thumbnailPath = thumbnailPath;
        this.hostEmail = hostEmail;
        this.title = title;
        this.currentUsers = currentUsers;
        this.expectedUsers = expectedUsers;
        this.roomHashTags = tags;
        this.users = users;
    }


    public static ResRoomEnter fromEntity(Room room) {

        return ResRoomEnter.builder()
                .roomId(room.getId())
                .thumbnailPath(room.getThumbnail().getImgPath())
                .hostEmail(room.getHostEmail())
                .title(room.getTitle())
                .expectedUsers(room.getExpectedUsers())
                .tags(toRoomTagOption(room.getRoomHashTags()))
                .users(convertUserList(room.getParticipants()))
                .currentUsers(getUserNum(room))
                .build();
    }

    private static List<String> toRoomTagOption(List<RoomHashTag> hashTags) {
        return hashTags.stream()
                .map(RoomHashTag::getTagName)
                .collect(Collectors.toList());
    }

    private static int getUserNum(Room room) {
        return room.getParticipants().size(); // 총 인원수는 방장도 포함하여 센다.
    }


    public static List<ResUserPreview> convertUserList(List<User> userList) {
        return userList.stream()
                .map(ResUserPreview::convertUser)
                .collect(Collectors.toList());
    }

    /*
    private static List<TagOption> toTagOption(List<RoomHashTag> hashTags) {
        return hashTags.stream()
                .map(RoomHashTag::getTagOption)
                .collect(Collectors.toList());
    }
    */
}

    