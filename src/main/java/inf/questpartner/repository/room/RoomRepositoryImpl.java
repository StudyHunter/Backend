package inf.questpartner.repository.room;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.questpartner.domain.room.Room;
import jakarta.persistence.EntityManager;  // jakarta.persistence.EntityManager로 수정

import org.springframework.stereotype.Repository;

//import static inf.questpartner.domain.room.QRoom.room;

@Repository
public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final EntityManager entityManager;

    public RoomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updateCount(Room room1, boolean b) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        if (b) {
            queryFactory.update(room1
                    .set(room1.likeCount, room1.likeCount.add(1))
                    .where(room1.eq(room1))
                    .execute();
        } else {
            queryFactory.update(room1)
                    .set(room1.likeCount, room1.likeCount.subtract(1))
                    .where(room1.eq(room1))
                    .execute();
        }
    }
}
