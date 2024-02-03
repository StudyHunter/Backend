package inf.questpartner.dto.users;

import inf.questpartner.service.encrytion.EncryptionService;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String token;

    public void passwordEncryption(EncryptionService encryptionService) {
        this.password = encryptionService.encrypt(password);
    }
}
