package inf.questpartner.config.login.auth;


import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 리포지토리를 통해 UserDetails 가져오는 서비스 생성
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;

	/**
	 * username을 가지고 UserDetails 객체를 리턴하는데,
	 * UserDetails의 구현체로 User Entity를 생성하였기에
	 * User Entity를 리턴하게끔 구현한 것!
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(username).orElseThrow(
				() -> new ResourceNotFoundException("User", "User Email : ", username));
	}

}
