package inf.questpartner.domain.room;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = 1241740159L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoom room = new QRoom("room");

    public final inf.questpartner.domain.chat.QChattingRoom chattingRoom;

    public final NumberPath<Integer> expectedUsers = createNumber("expectedUsers", Integer.class);

    public final StringPath hostEmail = createString("hostEmail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<inf.questpartner.domain.users.user.User, inf.questpartner.domain.users.user.QUser> participants = this.<inf.questpartner.domain.users.user.User, inf.questpartner.domain.users.user.QUser>createList("participants", inf.questpartner.domain.users.user.User.class, inf.questpartner.domain.users.user.QUser.class, PathInits.DIRECT2);

    public final ListPath<RoomHashTag, QRoomHashTag> roomHashTags = this.<RoomHashTag, QRoomHashTag>createList("roomHashTags", RoomHashTag.class, QRoomHashTag.class, PathInits.DIRECT2);

    public final EnumPath<inf.questpartner.domain.room.common.RoomStatus> roomStatus = createEnum("roomStatus", inf.questpartner.domain.room.common.RoomStatus.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> studyTimer = createNumber("studyTimer", Long.class);

    public final EnumPath<inf.questpartner.domain.room.common.RoomThumbnail> thumbnail = createEnum("thumbnail", inf.questpartner.domain.room.common.RoomThumbnail.class);

    public final StringPath title = createString("title");

    public QRoom(String variable) {
        this(Room.class, forVariable(variable), INITS);
    }

    public QRoom(Path<? extends Room> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoom(PathMetadata metadata, PathInits inits) {
        this(Room.class, metadata, inits);
    }

    public QRoom(Class<? extends Room> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingRoom = inits.isInitialized("chattingRoom") ? new inf.questpartner.domain.chat.QChattingRoom(forProperty("chattingRoom")) : null;
    }

}

