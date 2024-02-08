### Room 
```java
package com.example.chat.model.room;

import com.example.chat.model.room.common.RoomStatus;
import com.example.chat.model.room.common.RoomThumbnail;

import com.example.chat.model.room.common.RoomType;
import com.example.chat.model.room.common.tag.TagOption;
import com.example.chat.model.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.GenerationType.*;

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

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomHashTag> roomHashTags = new ArrayList<>(); // 방에 여러 태그를 붙일 수 있다.

    @Enumerated(EnumType.STRING)
    private RoomType roomType; // 방 유형 -> STUDY(스터디), PROJECT(팀 프로젝트)

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus; // 모집 상태 (자리 남았는지? OPEN /CLOSED)

    @Enumerated(EnumType.STRING)
    private RoomThumbnail thumbnail; // 섬네일 선택지

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<User> participants = new ArrayList<>(); // 방 참여자들 (연관 관계)

    @Builder(builderMethodName = "createRoom")
    public Room(String author, String title, int expectedUsers, RoomType roomType, RoomThumbnail thumbnail) {
        this.author = author;
        this.title = title;
        this.expectedUsers = expectedUsers;
        this.roomStatus = RoomStatus.OPEN;
        this.roomType = roomType;
        this.thumbnail = thumbnail;
    }

    public void addParticipant(User user) {
        this.participants.add(user);
    }

    public void addHashTag(RoomHashTag tag) {
        this.roomHashTags.add(tag);
    }

    @Override
    public String toString() {
        return "[Room Info] ->" + "방장명 = " + author +
                " 방제목 = " + title +
                ", 방 태그들 = " +
                ", 방 유형 = " + roomType +
                ", 방 썸네일 = " + thumbnail.getTypeInfo() +
                ", 방 제한된 인원수 = " + expectedUsers +
                ", 현재 모집상태 = " + roomStatus +
                ", 스터디에 참여한 회원이름" + participants.stream().map(User::getNickname).collect(Collectors.toList());
    }
}
```

<br><br>


### RoomHashTag
```java
package com.example.chat.model.room;

import com.example.chat.model.room.common.tag.TagOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoomHashTag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_hash_tag_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    private TagOption tagOption;

    @Builder
    public RoomHashTag(Room room, TagOption tagOption) {
        this.room = room;
        this.tagOption = tagOption;
    }
}
```

