package inf.questpartner.service;

import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.UserBanRequest;
import inf.questpartner.dto.users.UserDetailResponse;
import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.dto.users.UserSearchCondition;
import inf.questpartner.repository.admin.AdminRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.users.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserListResponse> findUsers(UserSearchCondition requestDto, Pageable pageable) {
        return adminRepository.searchByUsers(requestDto, pageable);
    }

    @Transactional(readOnly = true)
    public UserDetailResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("존재하지 않는 회원입니다."));
        return user.toUserDetailDto();
    }

    public void updateBanUsers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("존재하지 않는 회원입니다."));
        user.updateUserStatus();
        log.info("userStatus={}", user.getUserStatus());
    }
}