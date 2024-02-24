### RoomService
```java
package com.example.chat.service;

import com.example.chat.model.room.Room;
import com.example.chat.model.room.RoomHashTag;
import com.example.chat.model.room.common.tag.TagOption;
import com.example.chat.repository.RoomHashTagRepository;
import com.example.chat.repository.RoomRepository;
import com.example.chat.util.exception.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chat.dto.room.CreateRoomRequest;
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomHashTagRepository hashTagRepository;

    public Long createRoom(CreateRoomRequest req) {
        Room room = roomRepository.save(req.toRoomEntity());

        for (TagOption tag : req.getTags()) {
            RoomHashTag hashTag = hashTagRepository.save(new RoomHashTag(room, tag));
            room.addHashTag(hashTag);
        }
        return room.getId();
    }

    @Transactional(readOnly = true)
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundRoomException("존재하지 않는 방입니다."));
    }
}

```
