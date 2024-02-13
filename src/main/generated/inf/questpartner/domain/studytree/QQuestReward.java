package inf.questpartner.domain.studytree;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestReward is a Querydsl query type for QuestReward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestReward extends EntityPathBase<QuestReward> {

    private static final long serialVersionUID = 1024022491L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestReward questReward = new QQuestReward("questReward");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final QQuest quest;

    public QQuestReward(String variable) {
        this(QuestReward.class, forVariable(variable), INITS);
    }

    public QQuestReward(Path<? extends QuestReward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestReward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestReward(PathMetadata metadata, PathInits inits) {
        this(QuestReward.class, metadata, inits);
    }

    public QQuestReward(Class<? extends QuestReward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.quest = inits.isInitialized("quest") ? new QQuest(forProperty("quest"), inits.get("quest")) : null;
    }

}

