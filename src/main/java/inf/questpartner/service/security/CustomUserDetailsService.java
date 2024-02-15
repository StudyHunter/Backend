package inf.questpartner.service.security;


import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.security.SecurityUserDetailsDto;
import inf.questpartner.dto.users.UserDto;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.ApplicationException;
import inf.questpartner.util.exception.ErrorCodeV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 3-1. 시큐리티의 UserDetailsService를 구현한 메서드 사용자 정보를 통해 구분해서 로그인했는지 안했는지 판단한다.
 * 이 메서드는 사용자 정보를 로드하는 서비스를 생성한다.
 * 사용자 이름을 기반으로 사용자 정보를 데이터베이스에서 조회하고, 조회된 사용자 정보를 사용하여 SecurityUserDetailsDto 객체를 생성한다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // 1. userRepository로부터 loginId로 유저정보를 받아온다.
        User byLoginId = userRepository.findByEmail(loginId)
                .orElseThrow(
                        () -> new ApplicationException(ErrorCodeV2.USER_NOT_FOUND)
                );

        // 2.user를 dto로 변환시켜준다.
        UserDto userDto = UserDto.fromEntity(byLoginId);

        // 3. 사용자 정보를 기반으로 SecurityUserDetailsDto 객체를 생성한다.
        return new SecurityUserDetailsDto(
                userDto,
                Collections.singleton(new SimpleGrantedAuthority(
                        userDto.roleType().toString()
                ))
        );
    }

}