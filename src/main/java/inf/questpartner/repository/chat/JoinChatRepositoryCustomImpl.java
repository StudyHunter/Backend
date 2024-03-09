package inf.questpartner.repository.chat;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.questpartner.domain.chat.JoinChat;
import jakarta.persistence.EntityManager;
import java.util.List;
import static inf.questpartner.domain.chat.QChatBox.chatBox;
import static inf.questpartner.domain.chat.QJoinChat.*;
import static inf.questpartner.domain.users.user.QUser.user;

public class JoinChatRepositoryCustomImpl implements JoinChatRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public JoinChatRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<JoinChat> findByUserIdAndChatBoxId(String email, Long chatBoxId) {
        List<JoinChat> result = queryFactory.selectFrom(joinChat)
                .leftJoin(joinChat.chatBox, chatBox)
                .leftJoin(joinChat.user, user)
                .where(emailContains(email), chatRoomIdContains(chatBoxId))
                .fetch();

        return result;
    }

    private static BooleanExpression emailContains(String email) {
        return email != null ? joinChat.user.email.eq(email) : null;
    }

    private static BooleanExpression chatRoomIdContains(Long chatRoomId) {
        return chatRoomId != null ? joinChat.chatBox.id.eq(chatRoomId) : null;
    }
}
