package inf.questpartner.dto.users.req;

import inf.questpartner.domain.users.common.UserStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBanRequest {

    private Long id;
    private UserStatus userStatus;

    @Builder
    public UserBanRequest(Long id, UserStatus userStatus) {
        this.id = id;
        this.userStatus = userStatus;
    }
}
