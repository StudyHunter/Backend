package inf.questpartner.domain.users.common;

import inf.questpartner.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * UserBase는 User 테이블에 필요한 공통 사항을 정의한다.
 * (로그인 정보) 이메일 / 패스워드
 * 회원 권한구분 (User_Level)
 */

@Table(name = "USER_BASE")
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorColumn
public abstract class UserBase extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    protected String email;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected UserLevel userLevel;


}
