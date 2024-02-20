package inf.questpartner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/login_page")
    public String login_page() {
        return "login_page";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/myprofile")
    public String myprofile() {
        return "myprofile";
    }

}
