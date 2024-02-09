package inf.questpartner.domain.room;

import inf.questpartner.domain.room.common.RoomStatus;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.RoomTag;
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

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private int expectedSchedule; // 예상 스터디 시간(분 단위)

    private int likeCount;  //좋아요 수
    private int matchingScore;  //매칭 점수

    @Enumerated(EnumType.STRING)
    private List<TagOption> tags = new ArrayList<>(); // 방에 여러 태그를 붙일 수 있다.

    @Enumerated(EnumType.STRING)
    private RoomType roomType; // 방 유형 -> STUDY(스터디), PROJECT(팀 프로젝트)

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 모집 상태 (자리 남았는지? OPEN /CLOSED)

    @Enumerated(EnumType.STRING)
    private RoomThumbnail thumbnail; // 섬네일 선택지

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<User> participants = new ArrayList<>(); // 방 참여자들 (연관 관계)

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


//    N : M
    @OneToMany(mappedBy = "Room", orphanRemoval = true)
    private List<RoomUser> roomUserList = new ArrayList<>();
*/

    // 방 태그검색 테스트용 --
    public RoomTag toRoomTagDto() {
        return RoomTag.builder()
                .groupSize(this.expectedUsers)
                .expectedSchedule(this.expectedSchedule)
                .tagList(this.tags)
                .build();
    }


    public void addParticipant(User user) {
        this.participants.add(user);
    }

    @Override
    public String toString() {
        return "[Room Info] ->" + "방장명 = " + author +
                " 방제목 = " + title +
                ", 방 태그들 = " + tags.stream().map(TagOption::toString).collect(Collectors.joining(", ", "[", "]")) +
                ", 방 유형 = " + roomType +
                ", 방 썸네일 = " + thumbnail.getTypeInfo() +
                ", 방 제한된 인원수 = " + expectedUsers +
                ", 방 스터디 제한시간 = " + expectedSchedule +
                ", 현재 모집상태 = " + roomStatus +
                ", 스터디에 참여한 회원이름" + participants.stream().map(User::getNickname).collect(Collectors.toList());
    }
}
