package inf.questpartner.domain.users.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1888305063L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final inf.questpartner.domain.users.common.QUserBase _super = new inf.questpartner.domain.users.common.QUserBase(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final StringPath nickname = createString("nickname");

    //inherited
    public final StringPath password = _super.password;

    public final EnumPath<inf.questpartner.domain.users.common.UserProfileImg> profileImg = createEnum("profileImg", inf.questpartner.domain.users.common.UserProfileImg.class);

    public final StringPath roles = createString("roles");

    public final inf.questpartner.domain.room.QRoom room;

    public final NumberPath<Integer> studyTime = createNumber("studyTime", Integer.class);

    public final inf.questpartner.domain.studytree.QStudyTree studyTree;

    public final ListPath<UserWishHashTag, QUserWishHashTag> userHashTags = this.<UserWishHashTag, QUserWishHashTag>createList("userHashTags", UserWishHashTag.class, QUserWishHashTag.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<inf.questpartner.domain.users.common.UserLevel> userLevel = _super.userLevel;

    public final EnumPath<inf.questpartner.domain.users.common.UserStatus> userStatus = createEnum("userStatus", inf.questpartner.domain.users.common.UserStatus.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new inf.questpartner.domain.room.QRoom(forProperty("room")) : null;
        this.studyTree = inits.isInitialized("studyTree") ? new inf.questpartner.domain.studytree.QStudyTree(forProperty("studyTree")) : null;
    }

}

