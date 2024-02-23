package inf.questpartner.service;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.tag.TimerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Timer;

@Service
@Transactional
@RequiredArgsConstructor
public class TimerService {

    private Timer timer;
    private final RoomService roomService;
}
