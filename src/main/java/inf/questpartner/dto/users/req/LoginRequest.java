package inf.questpartner.dto.users.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String password;

    @Builder
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
