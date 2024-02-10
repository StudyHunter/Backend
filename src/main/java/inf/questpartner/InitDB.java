package inf.questpartner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;


import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomStatus;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static inf.questpartner.domain.room.common.tag.TagOption.*;

@Component
@RequiredArgsConstructor
public class InitDB {

//    void tedy() {
//        Room room = Room.roomBuild()
//                .author("방장")
//                .title("제목")
//                .expectedUsers(3)
//                .expectedSchedule(120)
//                .tags(Collections.singletonList(AI))
//                .roomType(RoomType.PROJECT)
//                .roomStatus(RoomStatus.OPEN)
//                .thumbnail(RoomThumbnail.APPLE)
//                .build();
//        //이넘 타입일 시, .dfdfd(dfdfd.AUTH)
//    }

}
