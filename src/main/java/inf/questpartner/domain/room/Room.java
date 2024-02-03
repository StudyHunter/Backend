package com.example.demo.domain;

import inf.questpartner.domain.common.RoomType;
import com.example.demo.domain.common.RoomThumbnail;
import com.example.demo.domain.common.RoomStatus;
import com.example.demo.domain.common.tag.TagOption;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private int expectedSchedule; // 예상 기간 (분단위)

    private int likeCount;  //좋아요 수
    private int matchingScore;  //매칭 점수

    @Enumerated(EnumType.STRING)
    private List<TagOption> tags = new ArrayList<>(); // 방에 여러 태그를 붙일 수 있다.

    @Enumerated(EnumType.STRING)
    private RoomType roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 모집 상태 (자리 남았는지?)

    @Enumerated(EnumType.STRING)
    private RoomThumbnail thumbnail; // 섬네일 선택지

    @Builder(builderMethodName = "roomBuild")
    public Room(Long id, String author, String title, int expectedUsers, int expectedSchedule, int likeCount, int matchingScore, List<TagOption> tags, RoomType roomType, RoomStatus roomStatus, RoomThumbnail thumbnail) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.expectedSchedule = expectedSchedule;
        this.likeCount = 0;
        this.matchingScore = 0;
        this.tags = tags;
        this.roomType = roomType;
        this.roomStatus = RoomStatus.OPEN;
        this.thumbnail = thumbnail;
    }
    /*
    // 1 : 1 양방향
    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Chatting chatting;

    //N : M
    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private List<RoomUser> roomUserList = new ArrayList<>();

    public void addRoomUser(RoomUser user) {
        roomUserList.add(user);
    }*/


}
