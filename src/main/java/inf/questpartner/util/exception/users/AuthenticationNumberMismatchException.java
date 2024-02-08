package inf.questpartner.util.exception.users;

public class AuthenticationNumberMismatchException extends RuntimeException {
    public AuthenticationNumberMismatchException(String message) {
        super(message);
    }
}
