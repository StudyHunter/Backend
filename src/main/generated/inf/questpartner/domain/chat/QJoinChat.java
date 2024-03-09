package inf.questpartner.domain.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJoinChat is a Querydsl query type for JoinChat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoinChat extends EntityPathBase<JoinChat> {

    private static final long serialVersionUID = -51467671L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJoinChat joinChat = new QJoinChat("joinChat");

    public final QChatBox chatBox;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final inf.questpartner.domain.users.user.QUser user;

    public QJoinChat(String variable) {
        this(JoinChat.class, forVariable(variable), INITS);
    }

    public QJoinChat(Path<? extends JoinChat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJoinChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJoinChat(PathMetadata metadata, PathInits inits) {
        this(JoinChat.class, metadata, inits);
    }

    public QJoinChat(Class<? extends JoinChat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatBox = inits.isInitialized("chatBox") ? new QChatBox(forProperty("chatBox")) : null;
        this.user = inits.isInitialized("user") ? new inf.questpartner.domain.users.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

