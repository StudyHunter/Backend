package inf.questpartner.domain.room;

import inf.questpartner.domain.chat.Chatting;
import inf.questpartner.domain.room.common.RoomStatus;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private int expectedSchedule; // 예상 기간

    @Enumerated(EnumType.STRING)
    private List<TagOption> tags = new ArrayList<>(); // 방에 여러 태그를 붙일 수 있다.

    @Enumerated(EnumType.STRING)
    private RoomType roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 모집 상태 (자리 남았는지?)

    @Enumerated(EnumType.STRING)
    private RoomThumbnail thumbnail; // 섬네일 선택지

    // n : 1 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 1 : 1 양방향
    @OneToOne(mappedBy = "ROOM", cascade = CascadeType.ALL, orphanRemoval = true)
    private Chatting chatting;
}
