package inf.questpartner.controller.api;

import inf.questpartner.controller.PageDto;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.dto.users.UserDetailResponse;
import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.dto.users.UserSearchCondition;
import inf.questpartner.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

//@Slf4j
//@Controller
//@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    // @Login(authority = UserLevel.ADMIN)
  /*
    @GetMapping("/users") //Page<UserListResponse>
    public String findByUsers(UserSearchCondition requestDto, Model model, @PageableDefault(size = 16) Pageable pageable) {
        Page<UserListResponse> result = adminService.findUsers(requestDto, pageable);

        model.addAttribute("users", result.getContent());
        model.addAttribute("userSearch", requestDto);
        model.addAttribute("userLevel", UserLevel.values());
        model.addAttribute("page", new PageDto(result.getTotalElements(), pageable));
        return "admin";
        //return adminService.findUsers(requestDto, pageable);
    }

    //@Login(authority = UserLevel.ADMIN)
    @GetMapping("/users/{id}")
    public String getUserDetail(@PathVariable(value = "id") Long id, Model model) {
        UserDetailResponse user = adminService.getUser(id);
        model.addAttribute("users", user);
        //return adminService.getUser(id);
        log.info("check id {}", user.getId());
        return "userDetail";
    }
   */
}
