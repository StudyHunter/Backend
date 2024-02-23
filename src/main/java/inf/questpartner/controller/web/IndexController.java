package inf.questpartner.controller.web;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
@Controller
public class IndexController {

    @GetMapping("/login_page")
    public String loginForm() {
        return "login_page";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }
}
