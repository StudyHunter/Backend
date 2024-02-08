package inf.questpartner.controller.response;

import inf.questpartner.util.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final ErrorCode errorCode;
    private final String message;
}
