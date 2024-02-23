package inf.questpartner.controller.dto;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.req.LoginRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CurrentUser implements Serializable {
    private String email;
   // private String token;

    public CurrentUser(LoginRequest req) {
        this.email = req.getEmail();
    }
}
