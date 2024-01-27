package inf.questpartner.domain.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChattingRoom is a Querydsl query type for ChattingRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChattingRoom extends EntityPathBase<ChattingRoom> {

    private static final long serialVersionUID = -215839864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChattingRoom chattingRoom = new QChattingRoom("chattingRoom");

    public final QChatting chatting;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roomName = createString("roomName");

    public final NumberPath<Integer> roomNumber = createNumber("roomNumber", Integer.class);

    public QChattingRoom(String variable) {
        this(ChattingRoom.class, forVariable(variable), INITS);
    }

    public QChattingRoom(Path<? extends ChattingRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChattingRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChattingRoom(PathMetadata metadata, PathInits inits) {
        this(ChattingRoom.class, metadata, inits);
    }

    public QChattingRoom(Class<? extends ChattingRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatting = inits.isInitialized("chatting") ? new QChatting(forProperty("chatting"), inits.get("chatting")) : null;
    }

}

