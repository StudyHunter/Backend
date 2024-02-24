### CreateRoomRequest : room 생성에 필요한 데이터 받기
```java
package com.example.chat.dto.room;

import com.example.chat.model.room.Room;
import com.example.chat.model.room.RoomHashTag;
import com.example.chat.model.room.common.RoomThumbnail;
import com.example.chat.model.room.common.RoomType;
import com.example.chat.model.room.common.tag.TagOption;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class CreateRoomRequest {

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private int expectedUsers; // 인원수 제한
    private RoomType roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)
    private RoomThumbnail thumbnail; // 썸네일 선택
    private List<TagOption> tags; // 방 태그추가 (RoomHahTag 테이블 정보)

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
```

<br><br>

### getRoomDetail : 생성된 방 정보 데이터 담기
```java
package com.example.chat.dto.room;

import com.example.chat.model.room.common.RoomThumbnail;
import com.example.chat.model.room.common.RoomType;
import com.example.chat.model.room.common.tag.TagOption;
import lombok.Data;

import java.util.List;
@Data
public class getRoomDetail {

    private String author; // 방장 닉네임
    private String title; // 방 제목
    private List<TagOption> tags; // 방에 여러 태그를 붙일 수 있다.
    private RoomType roomType; // 방 유형 ex: STUDY(스터디), PROJECT(팀 프로젝트)
    private RoomThumbnail thumbnail; // 썸네일 선택
}
```
