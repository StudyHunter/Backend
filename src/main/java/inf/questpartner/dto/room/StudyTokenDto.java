package inf.questpartner.dto.room;

import lombok.Data;

@Data
public class StudyTokenDto {
    private int studyToken;

    public StudyTokenDto(int studyToken) {
        this.studyToken = studyToken;
    }
}
