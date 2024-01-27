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

    public final StringPath author = createString("author");

    public final inf.questpartner.domain.chat.QChatting chatting;

    public final NumberPath<Integer> expectedSchedule = createNumber("expectedSchedule", Integer.class);

    public final NumberPath<Integer> expectedUsers = createNumber("expectedUsers", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Integer> matchingScore = createNumber("matchingScore", Integer.class);

    public final EnumPath<inf.questpartner.domain.room.common.RoomStatus> roomStatus = createEnum("roomStatus", inf.questpartner.domain.room.common.RoomStatus.class);

    public final EnumPath<inf.questpartner.domain.room.common.RoomType> roomType = createEnum("roomType", inf.questpartner.domain.room.common.RoomType.class);

    public final ListPath<RoomUser, QRoomUser> roomUserList = this.<RoomUser, QRoomUser>createList("roomUserList", RoomUser.class, QRoomUser.class, PathInits.DIRECT2);

    public final ListPath<inf.questpartner.domain.room.common.tag.TagOption, EnumPath<inf.questpartner.domain.room.common.tag.TagOption>> tags = this.<inf.questpartner.domain.room.common.tag.TagOption, EnumPath<inf.questpartner.domain.room.common.tag.TagOption>>createList("tags", inf.questpartner.domain.room.common.tag.TagOption.class, EnumPath.class, PathInits.DIRECT2);

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
        this.chatting = inits.isInitialized("chatting") ? new inf.questpartner.domain.chat.QChatting(forProperty("chatting"), inits.get("chatting")) : null;
    }

}

