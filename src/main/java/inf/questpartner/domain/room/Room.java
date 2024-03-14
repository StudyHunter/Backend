package inf.questpartner.domain.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inf.questpartner.domain.room.common.RoomStatus;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    private Long studyChatBoxId; // 채팅창 pk 저장하는 변수
    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한

    private LocalDateTime startTime;
    private long studyTimer; // 스터디 타이머
    private int currentUserNum; // 현재 인원 수


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<RoomHashTag> roomHashTags = new HashSet<>(); // 방에 여러 태그를 붙일 수 있다.

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 모집 상태 (자리 남았는지? OPEN /CLOSED)

    @Enumerated(EnumType.STRING)
    private RoomThumbnail thumbnail; // 섬네일 선택지

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  Set<User> participants = new HashSet<>(); // 방 참여자들 (연관 관계)


    @Builder(builderMethodName = "createRoom")
    public Room(String hostEmail, String title, int expectedUsers, RoomThumbnail thumbnail) {
        this.hostEmail = hostEmail;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomStatus = RoomStatus.OPEN;
        this.thumbnail = thumbnail;
        this.studyTimer = 0;
        this.currentUserNum = 0;
    }

    //수정
    public void patch(Room room) {
        if (room.title != null)
            this.title = room.title;
        if (room.thumbnail != null)
            this.thumbnail = room.thumbnail;
    }


    public void startRoomTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void endRoomTime(long studyTimer) {
        this.studyTimer = studyTimer;
    }

    // 스터디룸에 남은 자리가 있는지 확인하는 로직
    public boolean hasExceededUsers() {
        return this.participants.size() > expectedUsers;
    }

    // 스터디룸에 참여자 참여
    public void addParticipant(User user) {
        this.participants.add(user);
        user.setMappingRoom(this);
        this.currentUserNum += 1;
    }

   public void removeParticipant(User user) {
        this.participants.remove(user);
        this.currentUserNum -= 1;
    }

    // 방 삭제하기 위해, 모두 내보내기
   public void removeParticipantAll() {
        // user - room 매핑 관계 끊기
       for (User participant : this.participants) {
           participant.unsetMappingRoom();
       }
       // 회원정보 List<> 비우기
       this.participants.clear();
       this.currentUserNum = 0;
   }

    public void addHashTag(RoomHashTag tag) {
        this.roomHashTags.add(tag);
    }


    public void settingChatBox(Long chatBoxId) {
        this.studyChatBoxId = chatBoxId;
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
}
