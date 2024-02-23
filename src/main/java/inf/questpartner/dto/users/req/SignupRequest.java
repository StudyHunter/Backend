package inf.questpartner.dto.users.req;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
    private String nickname;
    private String password;
    private String passwordCheck;
    private String email;
    private List<TagOption> tags;


    @Builder
    public SignupRequest(String email, String password, String passwordCheck, String nickname) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
    }
    public User ofEntity() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .nickname(this.getNickname())
                .build();
    }

}
