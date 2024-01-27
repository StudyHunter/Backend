package inf.questpartner.dto.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;

import lombok.Data;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class CreateRoomRequest {

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private int expectedSchedule; // 예상 기간

    private List<String> tags; // 방에 여러 태그를 붙일 수 있다.

    private String roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)

    private String thumbnail;

    public Room toEntity(String userName) { //로그인 된 이용자(닉네임)으로 방 생성한다.
        return Room.roomBuild()
                .author(userName)
                .title(title)
                .expectedUsers(expectedUsers)
                .expectedSchedule(expectedSchedule)
                .tags(tagOptionList(tags))
                .roomType(RoomType.valueOf(roomType))
                .thumbnail(RoomThumbnail.valueOf(thumbnail))
                .build();
    }


    private List<TagOption> tagOptionList(List<String> tags) {
        return tags.stream()
                .map(TagOption::valueOf)
                .collect(Collectors.toList());
    }

    /*
    private List<TagOption> tagOptionList_old(List<String> tags) {
        List<TagOption> temps = new ArrayList<>();
        for (String tag : tags) {
            temps.add(TagOption.valueOf(tag));
        }
        return temps;
    }
     */



}
