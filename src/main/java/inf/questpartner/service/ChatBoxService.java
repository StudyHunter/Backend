package inf.questpartner.service;

import inf.questpartner.domain.chat.ChatBox;
import inf.questpartner.domain.chat.JoinChat;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.repository.chat.ChatBoxRepository;
import inf.questpartner.repository.chat.JoinChatRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.ResourceNotFoundException;
import inf.questpartner.util.exception.room.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatBoxService {


    private final ChatBoxRepository chatBoxRepository;
    private final JoinChatRepository joinChatRepository;
    private final UserRepository userRepository;

    //채팅창 생성
    public Long createRoom(User user) {
        User enterUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        ChatBox chatBox = createChatBox();
        JoinChat joinChat = createJoinChat(enterUser, chatBox);

        Long chatRoomId = chatBoxRepository.save(chatBox).getId();
        joinChatRepository.save(joinChat);

        return chatRoomId;
    }

   //스터디방에 새로운 사람이 들어왔다.
    public void joinToChatRoom(User user, Long chatRoomId) {
        User enterUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        ChatBox chatBox = chatBoxRepository.findById(chatRoomId)
                .orElseThrow(() -> new NotFoundRoomException("존재하지 않는 채팅방입니다."));

        JoinChat joinChat = createJoinChat(enterUser, chatBox);

        List<JoinChat> joinChatList = joinChatRepository.findByUserIdAndChatBoxId(enterUser.getEmail(), chatRoomId);
        if (joinChatList.isEmpty()) {
            log.info(enterUser.getNickname() + "님이 스터디방에 입장했습니다.");
            joinChatRepository.save(joinChat);
        }
    }

    // 테스트용
    public List<ChatBox> findAll() {
        return chatBoxRepository.findAll();
    }



    private static JoinChat createJoinChat(User user, ChatBox chatBox) {
        return JoinChat.builder()
                .user(user)
                .chatBox(chatBox)
                .build();
    }

    private static ChatBox createChatBox() {
        return ChatBox.builder()
                .name(UUID.randomUUID().toString())
                .build();
    }

}
