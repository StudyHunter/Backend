package inf.questpartner.service;

import inf.questpartner.domain.chat.ChattingRoom;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.chat.ChatRoomDto;
import inf.questpartner.repository.chat.ChattingRoomRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.chat.NotFoundChattingRoomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;



}
