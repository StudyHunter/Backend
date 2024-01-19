package inf.questpartner.domain.users.admin;

import inf.questpartner.domain.users.common.UserBase;
import inf.questpartner.domain.users.common.UserLevel;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends UserBase {

    @Builder
    public Admin(String email, String password, UserLevel userLevel) {
        this.email = email;
        this.password = password;
        this.userLevel = userLevel;
    }
}
