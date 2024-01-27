package inf.questpartner.util.exception.room;

public class NotFoundRoomException extends IllegalArgumentException {
    public NotFoundRoomException(String message) {
        super(message);
    }
}