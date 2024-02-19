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
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApiController {
    private final AdminService adminService;

    //@Login(authority = UserLevel.ADMIN)
    @GetMapping("/users") //Page<UserListResponse>
    public String findByUsers(@ModelAttribute("userSearch") UserSearchCondition requestDto, Model model,@PageableDefault(size = 8) Pageable pageable) {
        Page<UserListResponse> result = adminService.findUsers(requestDto, pageable);
        model.addAttribute("users", result.getContent());

        model.addAttribute("userLevels", UserLevel.values());

        log.info(requestDto.toString());
        PageDto page = new PageDto(result.getTotalElements(), pageable);
        model.addAttribute("page", page);

        //   return result;
        return "admin/admin-test-2";
        //return adminService.findUsers(requestDto, pageable);
    }

    //@Login(authority = UserLevel.ADMIN)
    @GetMapping("/users/{id}")
    public String getUserDetail(@PathVariable(value = "id") Long id, Model model) {
        UserDetailResponse user = adminService.getUser(id);
        model.addAttribute("users", user);

        log.info("check id {}", user.getId());
        log.info("check user = {}", user.toString());
        // return user;
        return "admin/userDetail-test";
    }

    @PostMapping("/users/{id}/ban")
    public String restrictUsers(@PathVariable("id") Long userId) {
        adminService.updateBanUsers(userId);

        UserDetailResponse user = adminService.getUser(userId);
        log.info("ban check ={}", user.toString());

        return "redirect:/admin/users";
    }

}