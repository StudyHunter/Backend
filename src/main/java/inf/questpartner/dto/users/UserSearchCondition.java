package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.UserLevel;
import lombok.*;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCondition {

    private Long id;
    private String email;
    private UserLevel userLevel;


}