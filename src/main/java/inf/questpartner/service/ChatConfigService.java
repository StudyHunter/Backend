package inf.questpartner.service;

import inf.questpartner.controller.response.ChatResponse;
import inf.questpartner.domain.chat.ChatConfigEntity;
import inf.questpartner.domain.chat.Chatting;
import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.chat.CheckResponse;
import inf.questpartner.repository.chat.ChatConfigRepository;

import inf.questpartner.repository.chat.ChattingRoomRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.AppException;
import inf.questpartner.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import inf.questpartner.dto.chat.CheckRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChatConfigService {

    private final ChatConfigRepository chatConfigRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;
   // private final ChattingRepository chattingRepository;
/*
    public ChatResponse checkSave(CheckRequest checkRequest) {
        Long roomId = checkRequest.getRoomId();
        String userName = checkRequest.getUserNickName();
        ChattingRoom chatRoom = chattingRoomRepository.findById(roomId).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,ErrorCode.DB_ERROR.getMessage()));
        User user = userRepository.findByNickname(userName);

        if(chatConfigRepository.existsByUserAndChattingRoom(user,chatRoom)){
            ChatConfigEntity chatConfigEntity = chatConfigRepository.findByUserAndChattingRoom(user,chatRoom).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,ErrorCode.DB_ERROR.getMessage()));
            chatConfigEntity.setTime();
            return ChatResponse.success(chatConfigEntity.getConfigTime());
        }else{
            ChatConfigEntity chatConfigEntity = ChatConfigEntity.builder().user(user).chattingRoom(chatRoom).configTime(LocalDateTime.now()).build();
            chatConfigRepository.save(chatConfigEntity);
            return ChatResponse.success(chatConfigEntity.getConfigTime());
        }
    }

    public ChatResponse checkRead(Long roomId, String userName){
        ChattingRoom chatRoom = chattingRoomRepository.findById(roomId).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,ErrorCode.DB_ERROR.getMessage()));
        User user = userRepository.findByNickname(userName);
        int index = 0;
        if(chatConfigRepository.existsByUserAndChattingRoom(user,chatRoom)){
            ChatConfigEntity check = chatConfigRepository.findByUserAndChattingRoom(user,chatRoom).orElseThrow(()->new AppException(ErrorCode.DB_ERROR,ErrorCode.DB_ERROR.getMessage()));
            List<Chatting> chats = chattingRepository.findByChattingRoom(chatRoom);
            for(Chatting chat : chats){
                if(chat.getCreatedDate().isBefore(check.getConfigTime())){
                    index++;
                }
            }
            return ChatResponse.success(CheckResponse.builder().index(index).build());
        }else{
            return ChatResponse.success(CheckResponse.builder().index(index).build());
        }
    }
 */
}
