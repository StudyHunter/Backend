package inf.questpartner.dto.room;


import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.tag.TagOption;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdatePostRequest {
    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private RoomType roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)
    private RoomThumbnail thumbnail; // 방 섬네일
    private List<TagOption> tags; // 방 태그표시들

    @Builder
    public UpdatePostRequest(String author, String title, int expectedUsers, RoomType roomType, RoomThumbnail thumbnail, List<TagOption> tags) {
        this.author = author;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomType = roomType;
        this.thumbnail = thumbnail;
        this.tags = tags;
    }
}
