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
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath nickname = createString("nickname");

    //inherited
    public final StringPath password = _super.password;

    public final inf.questpartner.domain.users.common.QUserProfileImg profileImg;

    public final ListPath<inf.questpartner.domain.room.Room, inf.questpartner.domain.room.QRoom> rooms = this.<inf.questpartner.domain.room.Room, inf.questpartner.domain.room.QRoom>createList("rooms", inf.questpartner.domain.room.Room.class, inf.questpartner.domain.room.QRoom.class, PathInits.DIRECT2);

    public final ListPath<inf.questpartner.domain.room.RoomUser, inf.questpartner.domain.room.QRoomUser> roomUserList = this.<inf.questpartner.domain.room.RoomUser, inf.questpartner.domain.room.QRoomUser>createList("roomUserList", inf.questpartner.domain.room.RoomUser.class, inf.questpartner.domain.room.QRoomUser.class, PathInits.DIRECT2);

    public final NumberPath<Integer> studyTime = createNumber("studyTime", Integer.class);

    public final inf.questpartner.domain.studytree.QStudyTree studyTree;

    public final ListPath<inf.questpartner.domain.room.common.tag.TagOption, EnumPath<inf.questpartner.domain.room.common.tag.TagOption>> tags = this.<inf.questpartner.domain.room.common.tag.TagOption, EnumPath<inf.questpartner.domain.room.common.tag.TagOption>>createList("tags", inf.questpartner.domain.room.common.tag.TagOption.class, EnumPath.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<inf.questpartner.domain.users.common.UserLevel> userLevel = _super.userLevel;

    public final EnumPath<inf.questpartner.domain.users.common.UserStatus> userStatus = createEnum("userStatus", inf.questpartner.domain.users.common.UserStatus.class);

    public final NumberPath<Integer> wishExpectedSchedule = createNumber("wishExpectedSchedule", Integer.class);

    public final NumberPath<Integer> wishGroupSize = createNumber("wishGroupSize", Integer.class);

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
        this.profileImg = inits.isInitialized("profileImg") ? new inf.questpartner.domain.users.common.QUserProfileImg(forProperty("profileImg")) : null;
        this.studyTree = inits.isInitialized("studyTree") ? new inf.questpartner.domain.studytree.QStudyTree(forProperty("studyTree")) : null;
    }

}

