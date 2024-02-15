package inf.questpartner.service;


import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.dto.chat.ChatRoomDto;
import inf.questpartner.repository.chat.ChattingRoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoomDto> chatRooms;
    private final ChattingRoomRepository chattingRoomRepository;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChattingRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChattingRoom chatRoom = ChattingRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();

        chatRooms.put(randomId, chatRoom.toDto());

        return chattingRoomRepository.save(chatRoom);
    }
}