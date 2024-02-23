package inf.questpartner.domain.users.common;

import lombok.Getter;

/**
 * 2. 회원 구분(User_Level)은 UNAUTH(미인증), AUTH(인증), ADMIN(관리자)로 3가지 존재한다.
 */
@Getter
public enum UserLevel {

    UNAUTH("UNAUTH", "미인증 회원"),
    USER("USER", "가입된 회원"),
    ADMIN("ADMIN", "관리자");


    private String code;
    private String levelInfo;

    UserLevel(String code, String levelInfo) {
        this.code = code;
        this.levelInfo = levelInfo;
    }


}
