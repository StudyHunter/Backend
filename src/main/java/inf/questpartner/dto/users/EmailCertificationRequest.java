package inf.questpartner.dto.users;

import lombok.Builder;
import lombok.Data;

@Data

public class EmailCertificationRequest {

    private String email;
    private String certificationNumber;

    @Builder
    public EmailCertificationRequest(String email, String certificationNumber) {
        this.email = email;
        this.certificationNumber = certificationNumber;
    }
}
