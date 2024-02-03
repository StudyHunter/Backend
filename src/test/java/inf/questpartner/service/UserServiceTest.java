package inf.questpartner.service;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.SaveRequest;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.service.encrytion.EncryptionService;
import inf.questpartner.util.exception.users.DuplicateEmailException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@Transactional
@Slf4j
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    EncryptionService encryptionService;

    private SaveRequest createSaveRequest() {
        return SaveRequest.builder()
                .nickname("test001")
                .email("test001@gmail.com")
                .password("1234!")
                .build();
    }

    private User createUser() {
        return createSaveRequest().toEntity();
    }

    @Test
    @DisplayName("이메일 중복으로 회원가입에 실패한다.")
    public void emailDuplicate() {
//        given: 테스트 유저가 등록되었을 때
        userService.save(createSaveRequest());

//        when: 중복된 이메일이 들어오는 경우
        SaveRequest saveRequest = SaveRequest.builder()
                .nickname("test002")
                .email("test001@gmail.com")
                .password("1234!")
                .build();

//        then: 에러가 발생한다
        assertThatThrownBy(() -> userService.save(saveRequest))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @DisplayName("회원가입이 정상적으로 진행된다.")
    @Test
    public void signup() {
        SaveRequest.builder()
                .nickname("test001")
                .password("1234!")
                .email("test001@gmail.com")
                .build();

    }

}