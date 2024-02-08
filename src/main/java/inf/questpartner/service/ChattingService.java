package inf.questpartner.service;

import inf.questpartner.controller.response.ChatResponse;
import inf.questpartner.domain.chat.Chatting;
import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.dto.chat.ChatMessageDto;

import inf.questpartner.repository.chat.ChattingRoomRepository;
import inf.questpartner.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import inf.questpartner.util.exception.AppException;

@Service
@RequiredArgsConstructor
@Transactional
public class ChattingService {

    /*
    private final ChattingRepository chatRepository;
    private final ChattingRoomRepository chattingRoomRepository;

    public ChatResponse addChat(ChatMessageDto chatMessageDto) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(chatMessageDto.getRoomId()).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,""));
        Chatting chat = chatRepository.save(chatMessageDto.toChat(chattingRoom));
        return ChatResponse.success(chat);
    }

    public List<ChatMessageDto> listChat(Long id) {
        ChattingRoom chatRoom = chattingRoomRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,""));
        List<Chatting> chats = chatRepository.findByChattingRoom(chatRoom);

        return chats.stream()
                .map(chat -> ChatMessageDto.builder()
                        .message(chat.getMessage())
                        .writer(chat.getWriter())
                        .createdAt(chat.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .collect(Collectors.toList());
    }

     */

}
