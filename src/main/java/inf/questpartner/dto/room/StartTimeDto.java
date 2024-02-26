package inf.questpartner.dto.room;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StartTimeDto {
    private LocalDateTime startTime;

    public StartTimeDto(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
