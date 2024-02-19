package inf.questpartner.service;

import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.LoginRequest;
import inf.questpartner.dto.users.UserInfoDto;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.service.encrytion.EncryptionService;
import inf.questpartner.util.exception.users.NotAuthorizedException;
import inf.questpartner.util.exception.users.UserNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static inf.questpartner.util.constant.UserConstants.AUTH_STATUS;
import static inf.questpartner.util.constant.UserConstants.USER_ID;

@Service
@RequiredArgsConstructor
//@Transactional
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
            throw new UserNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest) {
        existByEmailAndPassword(loginRequest);
        String email = loginRequest.getEmail();
        setUserLevel(email);
        session.setAttribute(USER_ID, email);
    }

    public void setUserLevel(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자 입니다."));

        banCheck(user);
        session.setAttribute(AUTH_STATUS, user.getUserLevel());
    }

    private void banCheck(User user) {
        if (user.isBan()) {
            throw new NotAuthorizedException("관리자에 의해 이용이 정지된 사용자 입니다.");
        }
    }

    public UserLevel getUserLevel() {
        return (UserLevel) session.getAttribute(AUTH_STATUS);
    }

    public void logout() {
        session.removeAttribute(USER_ID);
    }

    public String getLoginUser() {
        return (String) session.getAttribute(USER_ID);
    }

    @Transactional(readOnly = true)
    public UserInfoDto getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자 입니다.")).toUserInfoDto();
    }

}
