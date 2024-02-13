package inf.questpartner.dto.users;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
    private String token;

}
