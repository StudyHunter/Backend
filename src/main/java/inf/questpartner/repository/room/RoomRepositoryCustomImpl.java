package inf.questpartner.repository.room;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import static inf.questpartner.domain.room.QRoom.room;
import static inf.questpartner.domain.room.QRoomHashTag.roomHashTag;
import static inf.questpartner.domain.users.user.QUser.*;
import static org.springframework.util.StringUtils.hasText;

public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RoomRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<Room> findAllWithTagAndUser(Pageable pageable) {
        QueryResults<Room> result = queryFactory.selectFrom(room)
                .leftJoin(room.roomHashTags, roomHashTag).fetchJoin()
                .leftJoin(room.participants, user).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    @Override
    public Page<Room> findByTagOption(RoomSearchCondition condition, Pageable pageable) {
        QueryResults<Room> result = queryFactory.selectFrom(room)
                .leftJoin(room.roomHashTags, roomHashTag).fetchJoin()
                .leftJoin(room.participants, user).fetchJoin()
                .where(tagOptionContains(condition.getTagOption()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    private static BooleanExpression tagOptionContains(TagOption tagOption) {
        return tagOption != null ? roomHashTag.tagOption.eq(tagOption) : null;
    }


    private static BooleanExpression titleContains(String title) {
        return hasText(title) ? room.title.toLowerCase().like("%" + title.toLowerCase() + "%") : null;
    }
}
