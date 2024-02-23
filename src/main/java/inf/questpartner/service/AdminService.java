package inf.questpartner.service;

import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.res.UserDetailResponse;
import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.dto.users.UserSearchCondition;
import inf.questpartner.repository.admin.AdminRepository;
import inf.questpartner.repository.users.UserRepository;
import inf.questpartner.util.exception.users.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST));
        return user.toUserDetailDto();
    }

    public void updateBanUsers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST));
        user.updateUserStatus();
        log.info("userStatus={}", user.getUserStatus());
    }
}