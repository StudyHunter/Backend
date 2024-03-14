package inf.questpartner.controller.api.user;

import inf.questpartner.config.login.auth.CustomUserDetailsService;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.res.ResUserPreview;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final CustomUserDetailsService userDetailsService;

    @GetMapping("/api/user")
    public ResUserPreview getUserInfo(@AuthenticationPrincipal User user) {
        User checkUser = (User) userDetailsService.loadUserByUsername(user.getEmail());
        return ResUserPreview.convertUser(checkUser);
    }
}
