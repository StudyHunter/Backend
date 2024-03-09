package inf.questpartner.domain.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatBox is a Querydsl query type for ChatBox
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatBox extends EntityPathBase<ChatBox> {

    private static final long serialVersionUID = 228471724L;

    public static final QChatBox chatBox = new QChatBox("chatBox");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QChatBox(String variable) {
        super(ChatBox.class, forVariable(variable));
    }

    public QChatBox(Path<? extends ChatBox> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatBox(PathMetadata metadata) {
        super(ChatBox.class, metadata);
    }

}

