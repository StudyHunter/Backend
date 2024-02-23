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

    public static final QRoom room = new QRoom("room");

    public final StringPath author = createString("author");

    public final NumberPath<Integer> expectedUsers = createNumber("expectedUsers", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<inf.questpartner.domain.users.user.User, inf.questpartner.domain.users.user.QUser> participants = this.<inf.questpartner.domain.users.user.User, inf.questpartner.domain.users.user.QUser>createList("participants", inf.questpartner.domain.users.user.User.class, inf.questpartner.domain.users.user.QUser.class, PathInits.DIRECT2);

    public final ListPath<RoomHashTag, QRoomHashTag> roomHashTags = this.<RoomHashTag, QRoomHashTag>createList("roomHashTags", RoomHashTag.class, QRoomHashTag.class, PathInits.DIRECT2);

    public final EnumPath<inf.questpartner.domain.room.common.RoomStatus> roomStatus = createEnum("roomStatus", inf.questpartner.domain.room.common.RoomStatus.class);

    public final EnumPath<inf.questpartner.domain.room.common.RoomType> roomType = createEnum("roomType", inf.questpartner.domain.room.common.RoomType.class);

    public final EnumPath<inf.questpartner.domain.room.common.RoomThumbnail> thumbnail = createEnum("thumbnail", inf.questpartner.domain.room.common.RoomThumbnail.class);

    public final StringPath title = createString("title");

    public QRoom(String variable) {
        super(Room.class, forVariable(variable));
    }

    public QRoom(Path<? extends Room> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoom(PathMetadata metadata) {
        super(Room.class, metadata);
    }

}

