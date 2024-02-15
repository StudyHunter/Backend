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

    public static final QChattingRoom chattingRoom = new QChattingRoom("chattingRoom");

    public final ListPath<Chatting, QChatting> chattingList = this.<Chatting, QChatting>createList("chattingList", Chatting.class, QChatting.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath roomId = createString("roomId");

    public QChattingRoom(String variable) {
        super(ChattingRoom.class, forVariable(variable));
    }

    public QChattingRoom(Path<? extends ChattingRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChattingRoom(PathMetadata metadata) {
        super(ChattingRoom.class, metadata);
    }

}

