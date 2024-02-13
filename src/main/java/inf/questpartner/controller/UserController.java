package inf.questpartner.controller;

//import inf.questpartner.domain.users.user.User;
//import inf.questpartner.dto.users.LoginRequest;
//import inf.questpartner.dto.users.SaveRequest;
//import inf.questpartner.repository.users.UserRepository;
//import inf.questpartner.util.validation.argumentResolver.CurrentUser;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Slf4j
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    //회원 가입 페이지
//    @GetMapping("/users/signup")
//    public String newUserDto() {
//
//        return "users/new";
//    }
//
//    //회원 가입 페이지 user 저장
//    @PostMapping("/users/signup")
//    public String createUser(SaveRequest saveRequest) {
////        log.info(saveRequest.toString());
//        User user = saveRequest.toEntity();
//        User saved = userRepository.save(user);
//
//        return "users/login";
//    }
//
//    @GetMapping("/users/login")
//    public String loginPage() {
//        return "users/login";
//    }
//
//    //    @PostMapping("/users/login")
////    public String login(LoginRequest loginRequest) {
////
////    }
//    @GetMapping("/users/my-infos")
//    public String myInfoPage(@CurrentUser String email) {
//        userRepository.findByEmail(email);
//    }
//}
