package inf.questpartner.dto.room.res;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;
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

    private Long roomId; //스터디 방 pk

    private Long chatBoxId; // 채팅창 pk
    private String thumbnailPath;
    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private List<String> roomHashTags;
    private int currentUsers; //현재 인원

    @Builder
    public ResRoomPreview(Long roomId, Long chatBoxId, String thumbnailPath, String hostEmail, String title, int expectedUsers, List<String> roomHashTags, int currentUsers) {
        this.roomId = roomId;
        this.chatBoxId = chatBoxId;
        this.thumbnailPath = thumbnailPath;
        this.hostEmail = hostEmail;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomHashTags = roomHashTags;
        this.currentUsers = currentUsers;
    }

    public static ResRoomPreview fromEntity(Room room) {

        return ResRoomPreview.builder()
                .roomId(room.getId())
                .chatBoxId(room.getStudyChatBoxId())
                .thumbnailPath(room.getThumbnail().getImgPath())
                .hostEmail(room.getHostEmail())
                .title(room.getTitle())
                .expectedUsers(room.getExpectedUsers())
                .roomHashTags(toRoomTagOption(room.getRoomHashTags()))
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


    public static Page<ResRoomPreview> convert(Page<Room> roomPage) {
        return roomPage.map(ResRoomPreview::fromEntity);
    }


}
