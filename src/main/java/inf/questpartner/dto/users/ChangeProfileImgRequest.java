package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.UserProfileImg;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeProfileImgRequest {
    private String email;
    private UserProfileImg profileImg;


    @Builder
    public ChangeProfileImgRequest(String email, UserProfileImg profileImg) {

        this.email = email;
        this.profileImg = profileImg;
    }
}
