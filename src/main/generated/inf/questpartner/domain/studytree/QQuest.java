package inf.questpartner.domain.studytree;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuest is a Querydsl query type for Quest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuest extends EntityPathBase<Quest> {

    private static final long serialVersionUID = -1504152180L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuest quest = new QQuest("quest");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath questTitle = createString("questTitle");

    public final QStudyTree studyTree;

    public QQuest(String variable) {
        this(Quest.class, forVariable(variable), INITS);
    }

    public QQuest(Path<? extends Quest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuest(PathMetadata metadata, PathInits inits) {
        this(Quest.class, metadata, inits);
    }

    public QQuest(Class<? extends Quest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyTree = inits.isInitialized("studyTree") ? new QStudyTree(forProperty("studyTree")) : null;
    }

}

