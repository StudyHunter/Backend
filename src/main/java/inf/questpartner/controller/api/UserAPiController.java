package inf.questpartner.controller.api;


import inf.questpartner.common.annotation.CurrentUser;
import inf.questpartner.common.annotation.LoginCheck;
import inf.questpartner.dto.users.*;
import inf.questpartner.service.SessionLoginService;
import inf.questpartner.service.UserService;
import inf.questpartner.service.certification.EmailCertificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static inf.questpartner.util.constant.ResponseConstants.CREATED;
import static inf.questpartner.util.constant.ResponseConstants.OK;

/*
 예시
 프로필 이미지 변경하는 부분이 헷갈리는데
 프로필 이미지 경로만 이용하는 방법이 괜찮을까여
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserAPiController {

    private final UserService userService;

    private final SessionLoginService sessionLoginService;

    private final EmailCertificationService emailCertificationService;

    /*회원 가입 페이지에서 중복 이메일과 닉네임 검사*/
    @GetMapping("/signup/{email}/email-exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }

    @GetMapping("/signup/{nickname}/nickname-exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkNicknameDuplicate(nickname));
    }

    /*회원 가입 시 등록된 메일로 토큰 인증 메일 전송*/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SaveRequest requestDto) {
        userService.save(requestDto);
        emailCertificationService.sendEmailForEmailCheck(requestDto.getEmail());
        return CREATED;
    }

    /*등록된 메일 링크*/
    @GetMapping("/email-check-token")
    public void emailCheck(String token, String email) {
        userService.updateEmailVerified(token, email);
    }

    /*마이페이지에서 이메일 인증 재전송 버튼*/
    @PostMapping("/resend-email-token")
    public void resendEmailCheck(@CurrentUser String email) {
        emailCertificationService.sendEmailForEmailCheck(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        sessionLoginService.login(loginRequest);
    }

    @LoginCheck
    @DeleteMapping("/logout")
    public void logout() {
        sessionLoginService.logout();
    }

    /*해당 이메일이 등록 되어있는지 확인*/
    @GetMapping("/find/{email}")
    public ResponseEntity<FindUserResponse> findUser(@PathVariable String email) {
        FindUserResponse findUserResponse = userService.getUserResource(email);
        return ResponseEntity.ok(findUserResponse);
    }

    /*비밀번호 찾기 : 이메일 인증 후 비밀번호 변경 페이지로 이동*/
    @PostMapping("/email-certification/sends")
    public ResponseEntity sendEmail(@RequestBody EmailCertificationRequest requestDto) {
        emailCertificationService.sendEmailForCertification(requestDto.getEmail());
        return CREATED;
    }

    @PostMapping("/email-certification/confirms")
    public void emailVerification(@RequestBody EmailCertificationRequest requestDto) {
        emailCertificationService.verifyEmail(requestDto);
    }

    @PatchMapping("/forget/password")
    public void changePasswordByForget(@Valid @RequestBody ChangePasswordRequest requestDto) {
        userService.updatePasswordByForget(requestDto);
    }

    @GetMapping("/my-infos")
    public ResponseEntity<UserInfoDto> myPage(@CurrentUser String email) {
        UserInfoDto loginUser = sessionLoginService.getCurrentUser(email);
        return ResponseEntity.ok(loginUser);
    }

    @LoginCheck
    @PatchMapping("/password")
    public void changePassword(@CurrentUser String email,
                               @Valid @RequestBody ChangePasswordRequest requestDto) {
        userService.updatePassword(email, requestDto);
    }

    /*
    @LoginCheck
    @PatchMapping("/wish-tag")
    public void changeUserWishTag(@CurrentUser String email,
                                  @Valid @RequestBody ChangeUserWishTag requestDto) {
        userService.updateUserWishTag(email, requestDto);
    }

    @LoginCheck
    @PatchMapping("/profile-img")
    public void changeProfileImg(@CurrentUser String email,
                                 @Valid @RequestBody ChangeProfileImgRequest requestDto) {
        userService.updateProfileImg(email, requestDto);
    }


    @LoginCheck
    @DeleteMapping
    public ResponseEntity<Void> UserWithdrawal(@RequestBody PasswordRequest requestDto,
                                               @CurrentUser String email) {
        String password = requestDto.getPassword();
        userService.delete(email, password);
        sessionLoginService.logout();
        return OK;
    }
    */
}
