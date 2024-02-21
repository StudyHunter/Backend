```java
package com.example.chat.controller;


import com.example.chat.config.auth.PrincipalDetails;
import com.example.chat.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.chat.model.users.User;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class RestApiController {


	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}

	@GetMapping("/user")
	public PrincipalDetails user(Authentication authentication) {

		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		log.info("principal : "+principal.getUser().getId());
		log.info("principal : "+principal.getUser().getEmail());
		log.info("principal : "+principal.getUser().getPassword());
		log.info("user name={}", principal.getUser().getNickname());

		return principal;
	}

	// 어드민이 접근 가능
	@GetMapping("/admin/users")
	public List<User> users(){
		return userRepository.findAll();
	}

	@PostMapping("/join")
	public String join(@RequestBody User user) {
		user.updatePassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.settingRoles("ROLE_USER");
		user.updateNickname(user.getNickname());
		userRepository.save(user);
		return "회원가입완료";
	}





}
```










