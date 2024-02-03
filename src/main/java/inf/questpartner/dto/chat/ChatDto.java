package inf.questpartner.dto.chat;

import inf.questpartner.domain.chat.Chatting;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public class ChatDto {
    @OneToOne(mappedBy = "ROOM", cascade = CascadeType.ALL, orphanRemoval = true)
    private Chatting chatting;

}
