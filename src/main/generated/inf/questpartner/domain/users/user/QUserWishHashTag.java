package inf.questpartner.domain.users.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserWishHashTag is a Querydsl query type for UserWishHashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserWishHashTag extends EntityPathBase<UserWishHashTag> {

    private static final long serialVersionUID = -990652692L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserWishHashTag userWishHashTag = new QUserWishHashTag("userWishHashTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<inf.questpartner.domain.tag.TagOption> tagOption = createEnum("tagOption", inf.questpartner.domain.tag.TagOption.class);

    public final QUser user;

    public QUserWishHashTag(String variable) {
        this(UserWishHashTag.class, forVariable(variable), INITS);
    }

    public QUserWishHashTag(Path<? extends UserWishHashTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserWishHashTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserWishHashTag(PathMetadata metadata, PathInits inits) {
        this(UserWishHashTag.class, metadata, inits);
    }

    public QUserWishHashTag(Class<? extends UserWishHashTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

