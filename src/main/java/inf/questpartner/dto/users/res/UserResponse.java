package inf.questpartner.dto.users.res;

import inf.questpartner.domain.users.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    // 사용자 DB 인덱스 값을 굳이 사용자에게 노출시킬 필요는 없다고 생각
    private String email;


    @Builder
    public UserResponse(String email) {
        this.email = email;
    }

    // Entity -> DTO
    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .build();
    }
}
