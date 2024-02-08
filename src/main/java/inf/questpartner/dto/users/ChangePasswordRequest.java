package inf.questpartner.dto.users;

import inf.questpartner.service.encrytion.EncryptionService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요.")
    private String passwordAfter;
    private String passwordBefore;

    public void passwordEncryption(EncryptionService encryptionService) {
        this.passwordAfter = encryptionService.encrypt(passwordAfter);
        this.passwordBefore = encryptionService.encrypt(passwordBefore);
    }

    @Builder
    public ChangePasswordRequest(String email,
                                 @NotBlank(message = "비밀번호를 입력해주세요.") @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요.") String passwordAfter,
                                 String passwordBefore) {
        this.email = email;
        this.passwordAfter = passwordAfter;
        this.passwordBefore = passwordBefore;
    }
}

