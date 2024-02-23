package inf.questpartner.domain.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatting is a Querydsl query type for Chatting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatting extends EntityPathBase<Chatting> {

    private static final long serialVersionUID = -1505827571L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatting chatting = new QChatting("chatting");

    public final QChattingRoom chattingRoom;

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roomId = createString("roomId");

    public final StringPath sender = createString("sender");

    public final EnumPath<MessageType> type = createEnum("type", MessageType.class);

    public final inf.questpartner.domain.users.user.QUser user;

    public QChatting(String variable) {
        this(Chatting.class, forVariable(variable), INITS);
    }

    public QChatting(Path<? extends Chatting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatting(PathMetadata metadata, PathInits inits) {
        this(Chatting.class, metadata, inits);
    }

    public QChatting(Class<? extends Chatting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingRoom = inits.isInitialized("chattingRoom") ? new QChattingRoom(forProperty("chattingRoom")) : null;
        this.user = inits.isInitialized("user") ? new inf.questpartner.domain.users.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

