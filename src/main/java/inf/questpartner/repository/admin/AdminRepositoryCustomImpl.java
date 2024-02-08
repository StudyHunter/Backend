package inf.questpartner.repository.admin;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.questpartner.domain.users.common.UserLevel;


import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.dto.users.UserSearchCondition;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;


import static org.springframework.util.StringUtils.hasText;

public class AdminRepositoryCustomImpl {// implements AdminRepositoryCustom {

    /*
    private final JPAQueryFactory queryFactory;

    public AdminRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserListResponse> searchByUsers(UserSearchCondition searchCondition, Pageable pageable) {
        List<UserListResponse> content = queryFactory
                .select(new UserListResponse(user.id, user.email, user.userLevel))
                .from(user)
                .where(
                        userEmailEq(searchCondition.getEmail()),
                        userIdEq(searchCondition.getId()),
                        userLevelEq(searchCondition.getUserLevel())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory // count 쿼리
                .select(new UserListResponse(user.id, user.email, user.userLevel))
                .from(user)
                .where(
                        userEmailEq(searchCondition.getEmail()),
                        userIdEq(searchCondition.getId()),
                        userLevelEq(searchCondition.getUserLevel())
                ).fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanExpression userEmailEq(String userEmail) {
        return hasText(userEmail) ? user.email.endsWith(userEmail) : null;
    }

    private BooleanExpression userLevelEq(UserLevel userLevel) {
        return userLevel != null ? user.userLevel.eq(userLevel) : null;
    }

     */
}
