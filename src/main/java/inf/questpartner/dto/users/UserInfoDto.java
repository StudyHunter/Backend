package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.UserLevel;
import lombok.Builder;
import lombok.Data;

@Data
public class UserInfoDto {

    private String email;
    private String nickname;
    private UserLevel userLevel;

    @Builder
    public UserInfoDto(String email, String nickname, UserLevel userLevel) {
        this.email = email;
        this.nickname = nickname;
        this.userLevel = userLevel;
    }
}
