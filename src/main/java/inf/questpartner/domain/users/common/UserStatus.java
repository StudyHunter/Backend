package inf.questpartner.domain.users.common;

import lombok.Getter;

/**
 * 회원 상태(STATUS)는 BAN(관리자에 의해 차단), NOMAL 총 2가지로 존재한다.
 */
@Getter
public enum UserStatus {
    NORMAL("NORMAL", "회원 상태 양호"),
    BAN("BAN", "밴 처리된 회원");

    private String code;
    private String statusInfo;

    UserStatus(String code, String statusInfo) {
        this.code = code;
        this.statusInfo = statusInfo;
    }

}
