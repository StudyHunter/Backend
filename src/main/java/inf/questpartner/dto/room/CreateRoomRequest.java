package inf.questpartner.dto.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.tag.TagOption;
import lombok.Data;
import java.util.List;

@Data
public class CreateRoomRequest {

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private RoomType roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)
    private RoomThumbnail thumbnail; // 방 섬네일
    private List<TagOption> tags; // 방 태그표시들

    public Room toRoomEntity() {
        return Room.createRoom()
                .author(author)
                .title(title)
                .expectedUsers(expectedUsers)
                .roomType(roomType)
                .thumbnail(thumbnail)
                .build();
    }




}