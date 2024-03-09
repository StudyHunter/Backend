package inf.questpartner.service;

import inf.questpartner.domain.chat.Chat;
import inf.questpartner.domain.chat.ChatBox;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.chat.ChatDto;
import inf.questpartner.repository.chat.ChatRepository;
import inf.questpartner.repository.chat.ChatBoxRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.room.NotFoundRoomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatBoxRepository chatBoxRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    
    public ChatDto createChat(Long chatBoxId, String message, String nickname) {
        // 채팅을 작성한 회원 찾기
        User user = userRepository.findByNickname(nickname);

        // 스터디방의 채팅방 찾기
        ChatBox chatBox = chatBoxRepository.findById(chatBoxId).orElseThrow(() -> new NotFoundRoomException("존재하지 않는 방입니다."));

        // 채팅 엔티티 생성
        Chat chat = createChat(message, user, chatBox);
        chatRepository.save(chat);

        return ChatDto.createDto(chatBoxId, nickname, message);
    }

    // 해당 채팅창에 있는 모든 채팅 조회

    public List<ChatDto> findAllByChatBoxId(Long boxId) {
        List<Chat> chatHistory = chatRepository.findAllByChatBoxId(boxId);

        List<ChatDto> chatDtoList = chatHistory.stream()
                .map(chat -> ChatDto.builder()
                        .message(chat.getMessage())
                        .sender(chat.getUser().getNickname())
                        .sendDate(chat.getSendDate())
                        .build())
                .collect(Collectors.toList());

        return chatDtoList;
    }

    private static Chat createChat(String message, User user, ChatBox chatBox) {
        return Chat.builder()
                .chatBox(chatBox)
                .message(message)
                .user(user)
                .build();
    }

}
