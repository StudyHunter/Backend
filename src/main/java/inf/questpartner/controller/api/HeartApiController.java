package inf.questpartner.controller.api;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.service.HeartService;
import inf.questpartner.service.UserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class HeartApiController {

    private final HeartService heartService;
    private final UserService userService;

    @PostMapping("/heart/{roomId}")
    public void addHeart(@PathVariable("roomId") @Positive Long roomId, @AuthenticationPrincipal Long userId){    //로그인된 사용자 userId로 식별
        //사용자 id를 불러옴
        User user = userService.findById(userId);
        //방 id, 사용자를 추가
        heartService.addHeart(roomId, user);
    }
}
