package inf.questpartner.dto.like;


import lombok.Data;

@Data
public class LikeRequest {
    //좋아요를 한 userID와 roomID를 전달받음

    private Long userId;

    private Long roomId;

    /*
    *  public LikeRequest(Long userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }*/
}
