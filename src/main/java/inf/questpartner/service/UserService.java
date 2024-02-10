package inf.questpartner.service;

import inf.questpartner.domain.users.common.UserProfileImg;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.domain.users.user.UserWishHashTag;
import inf.questpartner.dto.users.*;
import inf.questpartner.repository.studytree.StudyTreeRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.service.certification.EmailCertificationService;
import inf.questpartner.service.encrytion.EncryptionService;
import inf.questpartner.util.exception.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final StudyTreeRepository studyTreeRepository;
    private final EmailCertificationService emailCertificationService;


    @Transactional
    public void save(SaveRequest requestDto) {
        if (checkEmailDuplicate(requestDto.getEmail())) {
            throw new DuplicateEmailException();
        }
        if (checkNicknameDuplicate(requestDto.getNickname())) {
            throw new DuplicateNicknameException();
        }
        requestDto.passwordEncryption(encryptionService);
        User user = userRepository.save(requestDto.toEntity());
        createRequiredInformation(user);
    }

    @Transactional(readOnly = true)
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("존재하지 않는 회원입니다."));
    }

    @Transactional(readOnly = true)
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    /*추가할 기능: 회원 가입 시 스터디 트리 자동 생성*/
    private void createRequiredInformation(User user) {
//        user.createStudyTree(studyTreeRepository.save(new StudyTree()));
    }

    @Transactional(readOnly = true)
    public FindUserResponse getUserResource(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 email 입니다.")).toFindUserDto();
    }

    @Transactional
    public void updatePasswordByForget(ChangePasswordRequest requestDto) {
        String email = requestDto.getEmail();
        requestDto.passwordEncryption(encryptionService);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        user.updatePassword(requestDto.getPasswordAfter());
    }

    @Transactional
    public void updatePassword(String email, ChangePasswordRequest requestDto) {
        requestDto.passwordEncryption(encryptionService);
        String passwordBefore = requestDto.getPasswordBefore();
        String passwordAfter = requestDto.getPasswordAfter();
        if (!userRepository.existsByEmailAndPassword(email, passwordBefore)) {
            throw new UnauthenticatedUserException("이전 비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        user.updatePassword(passwordAfter);
    }

    @Transactional
    public void updateUserWishTag(String email, ChangeUserWishTag requestDto) {
        int wishGroupSize = requestDto.getWishGroupSize();
        int wishExpectedSchedule = requestDto.getWishExpectedSchedule();
        List<UserWishHashTag> userHashTags = requestDto.getUserHashTags();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        user.updateUserWishTag(wishGroupSize, wishExpectedSchedule, userHashTags);
    }

    @Transactional
    public void updateProfileImg(String email, ChangeProfileImgRequest requestDto) {
        UserProfileImg profileImg = requestDto.getProfileImg();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        user.updateProfileImg(profileImg);
    }

    @Transactional
    public void delete(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));

        if (!userRepository.existsByEmailAndPassword(email, encryptionService.encrypt(password))) {
            throw new WrongPasswordException();
        }
        userRepository.deleteByEmail(email);
    }

    private void validToken(String token, String email) {
        emailCertificationService.verifyEmail(token, email);
    }

    //    이메일 인증 시 userLevel을 Auth로 설정
    @Transactional
    public void updateEmailVerified(String token, String email) {
        validToken(token, email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자 입니다."));
        user.updateUserLevel();
    }
}
