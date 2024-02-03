package com.example.demo.dto;

import com.example.demo.domain.Room;
import com.example.demo.domain.common.RoomThumbnail;
import com.example.demo.domain.common.RoomType;
import com.example.demo.domain.common.tag.TagOption;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.domain.common.tag.TagOption.AI;

@Data
public class CreateRoomRequest {

    private String author;  //방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private int expectedSchedule; // 예상 기간 (분단위)
    private List<String> tags; // 방에 여러 태그를 붙일 수 있다.
    private String roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)
    private String thumbnail; // 섬네일 선택지

    public Room toEntity(String userName){
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
}
