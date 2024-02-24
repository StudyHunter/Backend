### PrincipalDetailsService 

```java
package com.example.chat.config.auth;


import com.example.chat.model.users.User;
import com.example.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
		User user = userRepository.findByNickname(nickname);

		return new PrincipalDetails(user);
	}


}

```
