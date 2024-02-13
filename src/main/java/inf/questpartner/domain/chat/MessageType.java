package inf.questpartner.domain.chat;

/**
 * 사용자가 채팅방에 대한 행동: 참여(JOIN), 떠날때(LEAVE), 대화(CHAT)
 */
public enum MessageType {
    CHAT, JOIN, LEAVE
}
