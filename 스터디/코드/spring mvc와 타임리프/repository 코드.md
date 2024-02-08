### RoomRepository
```java
package com.example.chat.repository;

import com.example.chat.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
```

<br>

### RoomHashTagRepository
```java
package com.example.chat.repository;

import com.example.chat.model.room.RoomHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomHashTagRepository extends JpaRepository<RoomHashTag, Long> {
}
```

