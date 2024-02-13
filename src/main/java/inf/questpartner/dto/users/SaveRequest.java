package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.domain.users.user.User;
import lombok.Builder;
import lombok.Data;

@Data
public class SaveRequest {

    private String nickname;
    private String email;
    private String password;

    @Builder
    public SaveRequest(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .nickname(this.nickname)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
