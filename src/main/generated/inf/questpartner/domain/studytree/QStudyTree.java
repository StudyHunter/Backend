package inf.questpartner.domain.studytree;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyTree is a Querydsl query type for StudyTree
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyTree extends EntityPathBase<StudyTree> {

    private static final long serialVersionUID = 682352817L;

    public static final QStudyTree studyTree = new QStudyTree("studyTree");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Item, QItem> ItemList = this.<Item, QItem>createList("ItemList", Item.class, QItem.class, PathInits.DIRECT2);

    public final ListPath<Quest, QQuest> questList = this.<Quest, QQuest>createList("questList", Quest.class, QQuest.class, PathInits.DIRECT2);

    public QStudyTree(String variable) {
        super(StudyTree.class, forVariable(variable));
    }

    public QStudyTree(Path<? extends StudyTree> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyTree(PathMetadata metadata) {
        super(StudyTree.class, metadata);
    }

}

