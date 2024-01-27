package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDetailResponse {

    private Long id;
    private String email;
    private String nickname;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private UserLevel userLevel;
    private UserStatus userStatus;

    @Builder
    public UserDetailResponse(Long id, String email, String nickname, LocalDateTime modifiedDate, LocalDateTime createdDate, UserLevel userLevel, UserStatus userStatus) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
        this.userLevel = userLevel;
        this.userStatus = userStatus;
    }
}
