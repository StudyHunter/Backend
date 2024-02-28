package inf.questpartner.domain.users.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserBase;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserProfileImg;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.dto.users.res.UserDetailResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("USER")
@Entity
public class User extends UserBase implements UserDetails {

    private String nickname; // 닉네임

    private Integer studyTime; // 총 누적된 공부시간(분)

    private int studyToken; // 토큰 개수

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus; // 회원 상태(STATUS)는 BAN(관리자에 의해 차단), NORMAL

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserWishHashTag> userHashTags = new ArrayList<>(); // 방에 여러 태그를 붙일 수 있다.

    // private int wishGroupSize; // 스터디방 원하는 조건 : 인원 / 기간
    // private int wishExpectedSchedule;

    @Enumerated(EnumType.STRING)
    private UserProfileImg profileImg; // 회원 프로필 사진

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    @Builder
    public User(Long id, String email, String password, String nickname) {
        super(id, email, password, UserLevel.USER);
        this.nickname = nickname;
        this.userStatus = UserStatus.NORMAL;
        this.profileImg = UserProfileImg.IMG_FINN;
        this.studyTime = 0;
        this.studyToken = 0;
    }

    /**
     * 1 : 1 단방향
     * USER는 하나의 트리만 가질 수 있다.
     * 트리 또한 여러명의 유저가 함께 사용할 수 없다. 따라서 일대일 매핑으로 처리한다.
     */
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDY_TREE_ID")
    private StudyTree studyTree;


    // user 관련 테스트용으로 만든 것 (임시로 둔 것)
    public UserDetailResponse toUserDetailDto() {
        return UserDetailResponse.builder()
                .id(this.getId())
                .email(this.email)
                .nickname(this.nickname)
                .userLevel(this.userLevel)
                .userStatus(this.userStatus)
                .build();
    }

    public void addHashTag(UserWishHashTag tag) {
        this.userHashTags.add(tag);
    }

    public void setMappingRoom(Room room) {
        this.room = room;
    }

    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }


    // 관리자 권한으로 회원 BAN 처리하기 위한 로직
    public void updateUserStatus() {
        this.userStatus = UserStatus.BAN;
    }

    private String roles;

    public List<String> getRoleList() {
        if (this.roles == null) {
            return Collections.emptyList(); // 빈 목록 반환 또는 다른 처리 수행
        }
        return Arrays.asList(this.roles.split(","));
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void settingRoles() {
        this.roles = UserLevel.USER.getCode();
    }

    public void updateTotalTime(Integer time) {
        this.studyTime += time;
    }

    public void updateStudyToken(int studyToken) {
        this.studyToken += studyToken;
    }

    // 태그 알고리즘 로직들-- (수정중)

    public boolean checkUserBanStatus() {
        return this.userStatus == UserStatus.BAN;
    }


    public void addWishTags(UserWishHashTag tag) {
        this.userHashTags.add(tag);
    }


    public void updatePassword(String password) {
        this.password = password;
    }

    public boolean isBan() {
        return this.userStatus == UserStatus.BAN;
    }


    //========== UserDetails implements ==========//

    /**
     * Token을 고유한 Email 값으로 생성합니다
     *
     * @return email;
     */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.userLevel.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
