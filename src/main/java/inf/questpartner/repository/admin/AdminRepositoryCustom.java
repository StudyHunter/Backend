package inf.questpartner.repository.admin;

import inf.questpartner.dto.users.UserListResponse;
import inf.questpartner.controller.dto.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepositoryCustom {
    Page<UserListResponse> searchByUsers(UserSearchCondition userSearchCondition, Pageable pageable);
    
}
