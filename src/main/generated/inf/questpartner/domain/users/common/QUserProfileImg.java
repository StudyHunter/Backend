package inf.questpartner.domain.users.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserProfileImg is a Querydsl query type for UserProfileImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserProfileImg extends EntityPathBase<UserProfileImg> {

    private static final long serialVersionUID = -520463949L;

    public static final QUserProfileImg userProfileImg = new QUserProfileImg("userProfileImg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath profileImgPath = createString("profileImgPath");

    public QUserProfileImg(String variable) {
        super(UserProfileImg.class, forVariable(variable));
    }

    public QUserProfileImg(Path<? extends UserProfileImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserProfileImg(PathMetadata metadata) {
        super(UserProfileImg.class, metadata);
    }

}

