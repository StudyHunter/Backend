package inf.questpartner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/users/signup")
    public String newUserDto() {
        return "users/userSignup";
    }

    @GetMapping("/users/login")
    public String loginPage() {
        return "users/userLogin";
    }

    @GetMapping("/users/mainPage")
    public String mainPage() {
        return "users/mainPage";
    }

}
