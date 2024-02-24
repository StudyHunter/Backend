package inf.questpartner.domain.room;

import inf.questpartner.domain.room.common.RoomStatus;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;

import inf.questpartner.domain.room.common.tag.TimerStatus;
import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한

    private int studyTimer; // 스터디 타이머

    @Enumerated(EnumType.STRING)
    private TimerStatus timerStatus;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomHashTag> roomHashTags = new ArrayList<>(); // 방에 여러 태그를 붙일 수 있다.

   // @Enumerated(EnumType.STRING)
   // private RoomType roomType; // 방 유형 -> STUDY(스터디), PROJECT(팀 프로젝트)

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 모집 상태 (자리 남았는지? OPEN /CLOSED)

    @Enumerated(EnumType.STRING)
    private RoomThumbnail thumbnail; // 섬네일 선택지

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> participants = new ArrayList<>(); // 방 참여자들 (연관 관계)

    @Builder(builderMethodName = "createRoom")
    public Room(String hostEmail, String title, int expectedUsers, RoomThumbnail thumbnail) {
        this.hostEmail = hostEmail;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomStatus = RoomStatus.OPEN;
        this.thumbnail = thumbnail;
        this.studyTimer = 0;
    }

    // 스터디룸에 남은 자리가 있는지 확인하는 로직
    public boolean hasExceededUsers() {
        return this.participants.size() > expectedUsers;
    }

    // 스터디룸에 참여자 참여
    public void addParticipant(User user) {
        this.participants.add(user);
        user.setMappingRoom(this);
    }

   public void removeParticipant(User user) {
        this.participants.remove(user);
    }

    public void studyEnd(int time) {
        this.studyTimer = time;
    }

    public void addHashTag(RoomHashTag tag) {
        this.roomHashTags.add(tag);
    }

    @Override
    public String toString() {
        return "[Room Info] ->" + "방장 이메일 = " + hostEmail +
                " 방제목 = " + title +
                ", 방 태그들 = " + roomHashTags.stream().map(RoomHashTag::getTagOption).collect(Collectors.toList()) +
                ", 방 썸네일 = " + thumbnail.getTypeInfo() +
                ", 방 제한된 인원수 = " + expectedUsers +
                ", 현재 모집상태 = " + roomStatus +
                ", 스터디에 참여한 회원이름" + participants.stream().map(User::getNickname).collect(Collectors.toList());
    }


    // 방 태그검색 테스트용 --
    /*
    public RoomTag toRoomTagDto() {
        return RoomTag.builder()
                .groupSize(this.expectedUsers)
                .expectedSchedule(this.expectedSchedule)
                .tagList(this.tags)
                .build();
    }

     */


}
