package inf.questpartner.repository.room;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TagOption;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static inf.questpartner.domain.room.QRoom.room;
import static inf.questpartner.domain.room.QRoomHashTag.roomHashTag;
import static org.springframework.util.StringUtils.hasText;

public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RoomRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public Page<Room> searchPageSort(RoomSearchCondition condition, Pageable pageable) {
        List<Room> content = queryFactory.selectFrom(room)
                .leftJoin(room.roomHashTags, roomHashTag)
                .leftJoin(room.participants)
                .where(titleContains(condition.getTitle()),
                        tagOptionContains(condition.getTagOption()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        int totalSize = queryFactory.selectFrom(room)
                .leftJoin(room.roomHashTags, roomHashTag)
                .leftJoin(room.participants)
                .where(titleContains(condition.getTitle()),
                        tagOptionContains(condition.getTagOption()))
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    private static BooleanExpression tagOptionContains(TagOption tagOption) {
        return tagOption != null ? roomHashTag.tagOption.eq(tagOption) : null;
    }


    private static BooleanExpression titleContains(String title) {
        return hasText(title) ? room.title.toLowerCase().like("%" + title.toLowerCase() + "%") : null;
    }
}
