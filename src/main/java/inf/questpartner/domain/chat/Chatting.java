package inf.questpartner.domain.chat;

import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chatting {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "chatting_id")
    private Long id;

    private String content; // 메시지 내용
    private String sender; // 메시지 보내는 사람


    private String chatRoomId; // 방번호

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Chatting(String sender, MessageType type) {
        this.type = type;
        this.sender = sender;
    }


    // MessageType 설정
    public void settingType(MessageType type) {
        this.type = type;
    }

    // 메시지 입력
    public void enterContent(String content) {
        this.content = content;
    }

    // 메시지 보내는 이 지정
    public void settingSender(String sender) {
        this.sender = sender;
    }

}