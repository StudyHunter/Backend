package inf.questpartner.repository.admin;


import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.questpartner.domain.users.common.UserLevel;


import inf.questpartner.dto.users.QUserListResponse;
import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.dto.users.UserSearchCondition;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


import static inf.questpartner.domain.users.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

public class AdminRepositoryCustomImpl implements AdminRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AdminRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserListResponse> searchByUsers(UserSearchCondition searchCondition, Pageable pageable) {
        List<UserListResponse> content = queryFactory
                .select(new QUserListResponse(user.id, user.email, user.userLevel))
                .from(user)
                .where(
                        userEmailEq(searchCondition.getEmail()),
                        userLevelEq(searchCondition.getUserLevel())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory // count 쿼리
                .select(new QUserListResponse(user.id, user.email, user.userLevel))
                .from(user)
                .where(
                        userEmailEq(searchCondition.getEmail()),
                        userLevelEq(searchCondition.getUserLevel())
                ).fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    private BooleanExpression userEmailEq(String userEmail) {
        return hasText(userEmail) ? user.email.contains(userEmail) : null;
    }

    private BooleanExpression userLevelEq(UserLevel userLevel) {
        return userLevel != null ? user.userLevel.eq(userLevel) : null;
    }
}
