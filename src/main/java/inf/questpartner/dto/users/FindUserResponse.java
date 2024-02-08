package inf.questpartner.dto.users;

import lombok.Builder;
import lombok.Data;

@Data
public class FindUserResponse {

    private String email;

    @Builder
    public FindUserResponse(String email) {
        this.email = email;
    }
}

