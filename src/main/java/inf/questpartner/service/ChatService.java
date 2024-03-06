package inf.questpartner.service;

import inf.questpartner.controller.dto.ChatDto;
import inf.questpartner.domain.chat.Chat;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.repository.chat.ChatRepository;
import inf.questpartner.repository.room.RoomRepository;
import inf.questpartner.repository.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    public ChatDto createChat(Long roomId, String message, String loginId) {
        User user = userRepository.findByEmail(loginId)
                .orElseThrow(() -> new EntityNotFoundException());

        Room room = roomRepository.findById(roomId).orElseThrow();  //방 찾기 -> 없는 방일 경우 여기서 예외처리
        Chat chatEntity = Chat.builder()
                .room(room)
                .message(message)
                .user(user)
                .build();
        chatRepository.save(chatEntity);

        return ChatDto.builder()
                .roomId(chatEntity.getRoom().getId())
                .sender(user.getNickname())
                .message(chatEntity.getMessage())
                .build();
    }

    public List<ChatDto> findAllChatByRoomId(Long roomId) {
        List<Chat> chatHistory = chatRepository.findAllByRoomId(roomId);
        List<ChatDto> chatDtoList = new ArrayList<>();
        for (Chat chat : chatHistory) {
            ChatDto chatDto = ChatDto.builder()
                    .message(chat.getMessage())
                    .sender(chat.getUser().getNickname())
                    .sendDate(chat.getSendDate())
                    .build();
            chatDtoList.add(chatDto);
        }
        return chatDtoList;
    }
}