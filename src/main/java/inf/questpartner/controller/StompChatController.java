package inf.questpartner.controller;

import inf.questpartner.dto.chat.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StompChatController {

   private final SimpMessagingTemplate template;
    private final Map<String, Long> map = new HashMap<>();
    
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");

        updateLiveUsers(message);
        message.setStatus(0);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/out")
    public void out(ChatMessageDto message){
        message.setMessage(message.getWriter() + "님이 채팅방에 나가셨습니다.");

        removeUserAndUpdateMessage(message);
        message.setStatus(1);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto message){
        List<String> liveUser = getLiveUsersInRoom(message);
        message.setUserList(liveUser);
        message.setStatus(0);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    private List<String> getLiveUsersInRoom(ChatMessageDto message) {
        List<String> liveUser = new ArrayList<>();
        for(Map.Entry<String, Long> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        return liveUser;
    }

    private void removeUserAndUpdateMessage(ChatMessageDto message) {
        List<String> liveUser = new ArrayList<>();
        map.remove(message.getWriter());
        for(Map.Entry<String, Long> entry : map.entrySet()){
            if(entry.getValue().equals(message.getRoomId()) ){
                liveUser.add(entry.getKey());
            }
        }
        message.setUserList(liveUser);
    }



    private void updateLiveUsers(ChatMessageDto message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");

        map.put(message.getWriter(), message.getRoomId());

        List<String> liveUser = map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(message.getRoomId()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        message.setUserList(liveUser);
    }


}
