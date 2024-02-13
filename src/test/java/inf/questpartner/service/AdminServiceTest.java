package inf.questpartner.service;

import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.*;
import inf.questpartner.repository.admin.AdminRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class AdminServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;


    private SaveRequest createUserDto() {
        return SaveRequest.builder()
                .nickname("dawn")
                .email("dawn@naver.com")
                .password("!1234")
                .build();
    }

    private UserSearchCondition createSearchDto() {
        return UserSearchCondition.builder()
                .email("dawn@naver.com")
                .userLevel(UserLevel.AUTH)
                .build();
    }

    private List<User> setUsers() {
        List<User> list = new ArrayList<>();
        for (int i=0; i<2; i++) {
            SaveRequest requestDto = SaveRequest.builder()
                    .nickname("click" + i)
                    .email(i+ "Karina00@naver.com")
                    .password("num" + i)
                    .build();

            User user = userService.save(requestDto);
            list.add(user);
        }
        return list;
    }

    private List<UserListResponse> userListResponses() {
        SaveRequest request1 = SaveRequest.builder()
                .nickname("dawn")
                .email("dawn@naver.com")
                .password("!1234")
                .build();

        SaveRequest request2 = SaveRequest.builder()
                .nickname("keria")
                .email("keria@naver.com")
                .password("!1234!23")
                .build();

        User user1 = userService.save(request1);
        User user2 = userService.save(request2);


        UserListResponse dto1 = UserListResponse.builder()
                .id(user1.getId())
                .email(user1.getEmail())
                .userLevel(user1.getUserLevel())
                .build();

        UserListResponse dto2 = UserListResponse.builder()
                .id(user2.getId())
                .email(user2.getEmail())
                .userLevel(user2.getUserLevel())
                .build();



        return List.of(dto1, dto2);
    }

    @DisplayName("가입된 회원 전체를 조회한다.")
    @Test
    public void findAll() {
        List<UserListResponse> userList = userListResponses();
        int total = userList.size();
        PageRequest pageable = PageRequest.of(0, 10);
        Page<UserListResponse> result = new PageImpl<>(userList, pageable, total);

        UserSearchCondition searchCondition = UserSearchCondition.builder().userLevel(UserLevel.AUTH).build();

        Page<UserListResponse> userListResponses = adminService.findUsers(searchCondition, pageable);

       // assertThat(result.getContent().size()).isEqualTo(userListResponses.getContent().size());
        for (UserListResponse dto : userListResponses.getContent()) {
            System.out.println(dto.getEmail());
        }

    }

    @DisplayName("요청한 ID에 해당하는 사용자를 BAN 처리한다.")
    @Test
    public void updateBanUsers() {
        User user = userService.save(createUserDto());

        adminService.updateBanUsers(user.getId());

        assertThat(user.getUserStatus()).isEqualTo(UserStatus.BAN);
    }

    @Test
    @DisplayName("회원의 상세 정보를 조회한다.")
    public void searchUserDetail() {
        User user = userService.save(createUserDto());

        UserDetailResponse dto = adminService.getUser(user.getId());
        System.out.println(dto.toString());
    }


}