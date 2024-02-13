package inf.questpartner.dto.like;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> LikeResponse<T> success(T data) {
        return new LikeResponse<>(true, "success", data);
    }

    public static <T> LikeResponse<T> error(String message) {
        return new LikeResponse<>(false, message, null);
    }
}
