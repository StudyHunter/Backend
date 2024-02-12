package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.UserLevel;
import lombok.*;
import inf.questpartner.domain.users.common.UserLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchCondition {

    private String email;
    private UserLevel userLevel;

    @Builder
    // 생성자는 직접 작성하거나 필요한 곳에서 생성합니다.
    public UserSearchCondition(String email, UserLevel userLevel) {
        this.email = email;
        this.userLevel = userLevel;
    }
}
