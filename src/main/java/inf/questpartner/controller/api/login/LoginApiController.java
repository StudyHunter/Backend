package inf.questpartner.controller.api.login;

import inf.questpartner.config.login.jwt.JwtProperties;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.req.LoginRequest;
import inf.questpartner.dto.users.req.SignupRequest;
import inf.questpartner.dto.users.req.UserUpdate;
import inf.questpartner.dto.users.res.ResUserPreview;
import inf.questpartner.dto.users.res.UserResponse;
import inf.questpartner.dto.users.res.UserTokenDto;
import inf.questpartner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginApiController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(@Header("token") String token) {

        return jwtProperties.getUsernameFromToken(token);
    }

    @GetMapping("/checkId")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email) {
        userService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody SignupRequest dto) {
        UserResponse successMember = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody LoginRequest dto) {
        UserTokenDto loginDTO = userService.login(dto);

        return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<UserResponse> check(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> request) {
        String password = request.get("password");
        UserResponse memberInfo = userService.check(user, password);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(
            @AuthenticationPrincipal User user,
            @RequestBody UserUpdate dto) {
        UserResponse memberUpdate = userService.update(user, dto);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }

}
