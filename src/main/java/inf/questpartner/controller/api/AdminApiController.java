package inf.questpartner.controller.api;


import inf.questpartner.dto.users.UserBanRequest;
import inf.questpartner.dto.users.UserDetailResponse;
import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.dto.users.UserSearchCondition;
import inf.questpartner.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

 /*
    //@Login(authority = UserLevel.ADMIN)
    @PostMapping("/users/{id}/ban")
    public void restrictUser(@PathVariable("id") Long id, @RequestPart(value = "banRequest") UserBanRequest requestDto) {
        log.info("ban id ={}", id);
        adminService.updateBanUsers(requestDto);

    }
  */

    @GetMapping("/users") //Page<UserListResponse>
    public Page<UserListResponse> findByUsers(UserSearchCondition requestDto, Pageable pageable) {
       // Page<UserListResponse> result = adminService.findUsers(requestDto, pageable);

       // model.addAttribute("users", result.getContent());
       // model.addAttribute("userSearch", requestDto);
       // model.addAttribute("userLevel", UserLevel.values());
       // model.addAttribute("page", new PageDto(result.getTotalElements(), pageable));

        return adminService.findUsers(requestDto, pageable);
    }

    //@Login(authority = UserLevel.ADMIN)
    @GetMapping("/users/{id}")
    public UserDetailResponse getUserDetail(@PathVariable(value = "id") Long id, Model model) {
        UserDetailResponse user = adminService.getUser(id);
        model.addAttribute("users", user);
        //return adminService.getUser(id);
        log.info("check id {}", user.getId());
        return user;
    }

    @PostMapping("/users/ban")
    public void restrictUsers(@RequestBody UserBanRequest requestDto) {
        adminService.updateBanUsers(requestDto);
    }



}
