package inf.questpartner.dto.users;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.common.UserProfileImg;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.service.encrytion.EncryptionService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SaveRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요.")
    private String password;

    private UserProfileImg profileImg;

    private List<TagOption> tags;

    private int wishGroupSize;

    private int wishExpectedSchedule;

    @Builder
    public SaveRequest(String nickname, String email, String password, UserProfileImg profileImg,
                       int wishGroupSize, int wishExpectedSchedule) {

        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profileImg = profileImg;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
    }

    public void passwordEncryption(EncryptionService encryptionService) {
        this.password = encryptionService.encrypt(password);
    }

    public User toEntity() {
        return User.builder()
                .nickname(this.nickname)
                .email(this.email)
                .password(this.password)
                .profileImg(this.profileImg)
                .wishGroupSize(this.wishGroupSize)
                .wishExpectedSchedule(this.wishExpectedSchedule)
                .build();
    }
}
