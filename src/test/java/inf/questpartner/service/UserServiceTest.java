package inf.questpartner.service;

import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.ChangePasswordRequest;
import inf.questpartner.dto.users.FindUserResponse;
import inf.questpartner.dto.users.SaveRequest;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.service.encrytion.EncryptionService;
import inf.questpartner.util.exception.users.DuplicateEmailException;
import inf.questpartner.util.exception.users.DuplicateNicknameException;
import inf.questpartner.util.exception.users.NotFoundUserException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

//    @Autowired
//    SessionLoginService sessionLoginService;

    private List<TagOption> createTestTags() {
        List<TagOption> list = new ArrayList<>();
        list.add(TagOption.TEAMPROJECT);
        list.add(TagOption.JAVA);
        return list;
    }

    private SaveRequest createSaveRequest() {
        return SaveRequest.builder()
                .nickname("test001")
                .email("test001@gmail.com")
                .password("12341234!")
//                .profilePath("testProfilePath1")
                .tags(createTestTags())
                .wishGroupSize(5)
                .wishExpectedSchedule(5)
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
                .password("12341234!")
//                .profilePath("testProfilePath1")
                .tags(createTestTags())
                .wishGroupSize(5)
                .wishExpectedSchedule(5)
                .build();

        //        then: 에러가 발생한다
        assertThatThrownBy(() -> userService.save(saveRequest))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    @DisplayName("닉네임 중복으로 회원가입에 실패한다.")
    public void nicknameDuplicate() {
        //        given: 테스트 유저가 등록되었을 때
        userService.save(createSaveRequest());

        //        when: 중복된 닉네임이 들어오는 경우
        SaveRequest saveRequest = SaveRequest.builder()
                .nickname("test001")
                .email("test002@gmail.com")
                .password("12341234!")
//                .profilePath("testProfilePath1")
                .tags(createTestTags())
                .wishGroupSize(5)
                .wishExpectedSchedule(5)
                .build();

        //        then: 에러가 발생한다
        assertThatThrownBy(() -> userService.save(saveRequest))
                .isInstanceOf(DuplicateNicknameException.class);
    }

    @Test
    @DisplayName("비밀번호 찾기 성공 - 전달받은 객체(이메일)가 회원이라면 비밀번호 변경에 성공한다.")
    public void updatePasswordByForget() {
        //        given: 테스트 유저가 등록되었을 때
        userService.save(createSaveRequest());

        //        when: 비밀번호를 변경한 경우
        ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.builder()
                .email("test001@gmail.com")
                .passwordAfter("12345678!")
                .build();
        userService.updatePasswordByForget(changePasswordRequest);
        User user = userRepository.findByEmail(changePasswordRequest.getEmail())
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        //        then: 등록된 유저의 비밀번호와 변경하려는 비밀번호가 동일하다.
        assertThat(user.getPassword()).isEqualTo(changePasswordRequest.getPasswordAfter());
    }

    @Test
    @DisplayName("가입된 이메일 입력시 비밀번호 찾기(재설정)에 필요한 리소스를 리턴한다.")
    public void SuccessToGetUserResource() {
        //        given: 테스트 유저가 등록되었을 때
        userService.save(createSaveRequest());
        User user = userRepository.findByEmail(createSaveRequest().getEmail())
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        //        when: 리소스를 요청한 경우
        FindUserResponse userResource = userService.getUserResource(createSaveRequest().getEmail());

        //        then: 필요한 리소스를 리턴한다.
        assertThat(userResource.getEmail()).isEqualTo(user.getEmail());
    }


    @Test
    @DisplayName("존재하지 않는 이메일 입력시 비밀번호 찾기(재설정)에 필요한 리소스 리턴에 실패한다.")
    public void failToGetUserResource() {
        //        given: 테스트 유저가 등록되었을 때
        userService.save(createSaveRequest());

//        when: 존재하지 않는 이메일을 입력시
        String email = "non@gmail.com";

//        then: 에러가 발생한다.
        assertThatThrownBy(() -> userService.getUserResource(email))
                .isInstanceOf(NotFoundUserException.class);
    }

}