package inf.questpartner.dto.users;

import lombok.Builder;
import lombok.Data;

@Data
public class ChangeProfileImgRequest {
    private String email;
    private String profilePath;


    @Builder
    public ChangeProfileImgRequest(String email, String profilePath) {

        this.email = email;
        this.profilePath = profilePath;
    }
}
