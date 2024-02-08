package inf.questpartner.domain.room;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomHashTag is a Querydsl query type for RoomHashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoomHashTag extends EntityPathBase<RoomHashTag> {

    private static final long serialVersionUID = 960908077L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomHashTag roomHashTag = new QRoomHashTag("roomHashTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRoom room;

    public final EnumPath<inf.questpartner.domain.room.common.tag.TagOption> tagOption = createEnum("tagOption", inf.questpartner.domain.room.common.tag.TagOption.class);

    public QRoomHashTag(String variable) {
        this(RoomHashTag.class, forVariable(variable), INITS);
    }

    public QRoomHashTag(Path<? extends RoomHashTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomHashTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomHashTag(PathMetadata metadata, PathInits inits) {
        this(RoomHashTag.class, metadata, inits);
    }

    public QRoomHashTag(Class<? extends RoomHashTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room")) : null;
    }

}

