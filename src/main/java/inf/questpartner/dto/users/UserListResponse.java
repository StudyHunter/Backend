package inf.questpartner.dto.users;

import com.querydsl.core.annotations.QueryProjection;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserListResponse {

    private Long id;
    private String email;
    private UserLevel userLevel;

    @QueryProjection
    public UserListResponse(Long id, String email, UserLevel userLevel) {
        this.id = id;
        this.email = email;
        this.userLevel = userLevel;
    }

    public static UserListResponse toDto(User user) {
        return UserListResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userLevel(user.getUserLevel())
                .build();
    }

}
