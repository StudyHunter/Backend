package inf.questpartner.dto.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
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

    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private List<TagOption> roomHashTags;
    private List<String> users;

    @Builder
    public ResRoomEnter(Long roomId, String hostEmail, String title, int expectedUsers, List<TagOption> tags, List<String> users) {
        this.roomId = roomId;
        this.hostEmail = hostEmail;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomHashTags = tags;
        this.users = users;
    }

    public static ResRoomEnter fromEntity(Room room) {

        return ResRoomEnter.builder()
                .roomId(room.getId())
                .hostEmail(room.getHostEmail())
                .title(room.getTitle())
                .expectedUsers(room.getExpectedUsers())
                .tags(toTagOption(room.getRoomHashTags()))
                .users(toUserList(room.getParticipants()))
                .build();
    }

    private static List<TagOption> toTagOption(List<RoomHashTag> hashTags) {
        return hashTags.stream()
                .map(RoomHashTag::getTagOption)
                .collect(Collectors.toList());
    }

    private static List<String> toUserList(List<User> users) {
        return users.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }

}

    