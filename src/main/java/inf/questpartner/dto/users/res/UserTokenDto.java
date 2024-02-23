package inf.questpartner.dto.users.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * -Response-
 * 사용자 정보 반환 + token Dto
 */

@Getter
@Setter
@NoArgsConstructor
public class UserTokenDto {
    private String email;
    private String token;

    @Builder
    public UserTokenDto(String email, String token) {
        this.email = email;
        this.token = token;
    }

    // Entity -> DTO
    public static UserTokenDto fromEntity(UserDetails member, String token) {
        return UserTokenDto.builder()
                .email(member.getUsername())
                .token(token)
                .build();
    }
}
