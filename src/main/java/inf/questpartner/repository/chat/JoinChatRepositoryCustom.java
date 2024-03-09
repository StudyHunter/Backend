package inf.questpartner.repository.chat;

import inf.questpartner.domain.chat.JoinChat;

import java.util.List;

public interface JoinChatRepositoryCustom {
    List<JoinChat> findByUserIdAndChatBoxId(String email, Long chatBoxId);
}
