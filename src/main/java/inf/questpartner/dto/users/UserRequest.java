package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.RoleType;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.domain.users.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserRequest {

    Long userId;
    String username;
    String password;
    UserStatus status;
    String email;
    RoleType roleType;


    @Builder
    private UserRequest(Long userId, String username, String password, UserStatus status, String email, RoleType roleType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        this.roleType = roleType;
    }

    // factory method 선언
    public static UserRequest of(Long userId, String username, String password, UserStatus status, String email, RoleType roleType) {
        return new UserRequest(
                userId, username, password, status, email, roleType);
    }

    // entity -> request 변환 메서드
    public static UserRequest fromEntity(User entity) {
        return new UserRequest(
                entity.getId(),
                entity.getNickname(),
                entity.getPassword(),
                entity.getUserStatus(),
                entity.getEmail(),
                RoleType.USER
        );
    }

}