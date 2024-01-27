package inf.questpartner.service;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.QUserListResponse;
import inf.questpartner.dto.users.SaveRequest;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.users.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(SaveRequest requestDto) {
        // 검증 로직 부분

        return userRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("존재하지 않는 회원입니다."));
    }

    @Transactional(readOnly = true)
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
