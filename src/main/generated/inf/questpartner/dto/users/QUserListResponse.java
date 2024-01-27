package inf.questpartner.dto.users;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * inf.questpartner.dto.users.QUserListResponse is a Querydsl Projection type for UserListResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserListResponse extends ConstructorExpression<UserListResponse> {

    private static final long serialVersionUID = 1164921562L;

    public QUserListResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<inf.questpartner.domain.users.common.UserLevel> userLevel) {
        super(UserListResponse.class, new Class<?>[]{long.class, String.class, inf.questpartner.domain.users.common.UserLevel.class}, id, email, userLevel);
    }

}

