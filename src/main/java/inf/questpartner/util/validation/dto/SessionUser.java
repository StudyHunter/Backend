package inf.questpartner.util.validation.dto;

import inf.questpartner.domain.users.user.User;
import lombok.Getter;


import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String nickName;

    public SessionUser(User user) {
        this.nickName = user.getNickname();
    }
}
