package inf.questpartner.domain.room.common.tag;

import lombok.Getter;

@Getter
public enum TimerStatus {

    STOPPED("타이머 중지된 상태"),
    RUNNING("타이머가 현재 실행 중인 상태"),
    PAUSED("타이머 일시정지 상태");

    private String statusInfo;

    TimerStatus(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
