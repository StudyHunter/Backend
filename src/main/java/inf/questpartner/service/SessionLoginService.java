package inf.questpartner.service;

import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.LoginRequest;
import inf.questpartner.dto.users.UserInfoDto;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.users.NotAuthorizedException;
import inf.questpartner.util.exception.users.NotFoundUserException;
import inf.questpartner.service.encrytion.EncryptionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static inf.questpartner.util.constant.UserConstants.AUTH_STATUS;
import static inf.questpartner.util.constant.UserConstants.USER_ID;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionLoginService {

    private final HttpSession session;
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;

    @Transactional(readOnly = true)
    public void existByEmailAndPassword(LoginRequest loginRequest) {
        loginRequest.passwordEncryption(encryptionService);
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new NotFoundUserException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest) {
        existByEmailAndPassword(loginRequest);
        String email = loginRequest.getEmail();
        validateUserLevel(email);
        session.setAttribute(USER_ID, email);
    }

    public void logout() {
        session.removeAttribute(USER_ID);
    }

    // 회원 권한 검증 로직
    public void validateUserLevel(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 회원입니다."));

        checkUserBanStatus(user);
        session.setAttribute(AUTH_STATUS, user.getUserLevel());
    }

    // 밴 검증 로직
    private void checkUserBanStatus(User user) {
        if (user.checkUserBanStatus()) {
            throw new NotAuthorizedException("관리자에 의해 이용이 정지된 회원입니다.");
        }
    }

    // 회원 권한 불러오기 로직
    public UserLevel retrieveUserLevel() {
        return (UserLevel) session.getAttribute(AUTH_STATUS);
    }

    // 회원 정보 불러오기 로직
    public String retrieveLoginUser() {
        return (String) session.getAttribute(USER_ID);
    }

    @Transactional(readOnly = true)
    public UserInfoDto getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다.")).toUserInfoDto();
    }
}
