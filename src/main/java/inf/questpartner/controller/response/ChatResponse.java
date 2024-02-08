package inf.questpartner.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.ErrorResponse;

@AllArgsConstructor
@Getter
public class ChatResponse<T> {

    private String resultCode;
    private T result;

    public static ChatResponse<ErrorResponse> error(ErrorResponse errorResponse) {
        return new ChatResponse("ERROR", errorResponse);
    }

    public static <T> ChatResponse<T> success(T result) {
        return new ChatResponse("SUCCESS", result);
    }
}
