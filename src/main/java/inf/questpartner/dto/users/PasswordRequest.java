package inf.questpartner.dto.users;

import lombok.Builder;
import lombok.Data;

@Data
public class PasswordRequest {

    private String password;

    @Builder
    public PasswordRequest(String password) {
        this.password = password;
    }
}
