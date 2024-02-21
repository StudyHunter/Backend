### User
```java
package com.example.chat.model.users;

import com.example.chat.dto.user.UserDetailResponse;
import com.example.chat.dto.user.UserInfoDto;
import com.example.chat.dto.user.UserWishTag;
import com.example.chat.model.room.Room;
import com.example.chat.model.room.common.tag.TagOption;
import com.example.chat.model.users.common.UserBase;
import com.example.chat.model.users.common.UserLevel;

import com.example.chat.model.users.common.UserProfileImg;
import com.example.chat.model.users.common.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("USER")
@Entity
public class User extends UserBase {

    private String nickname; // 닉네임

    private int studyTime; // 총 누적된 공부시간

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus; // 회원 상태(STATUS)는 BAN(관리자에 의해 차단), NOMAL

    @Enumerated(EnumType.STRING)
    private List<TagOption> tags = new ArrayList<>(); // 취향태그 저장

    private int wishGroupSize; // 스터디방 원하는 조건 : 인원 / 기간
    private int wishExpectedSchedule;

    @Enumerated(EnumType.STRING)
    private UserProfileImg profileImg; // 회원 프로필 사진

    @JsonIgnore
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    @Builder
    public User(Long id, String email, String password,
                String nickname, UserProfileImg profileImg, int wishGroupSize, int wishExpectedSchedule, List<TagOption> tags) {
        super(id, email, password, UserLevel.AUTH);
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.userStatus = UserStatus.NORMAL;
        this.studyTime = 0;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.tags = tags;
    }

    // 관리자 권한으로 회원 BAN 처리하기 위한 로직
    public void updateUserStatus() {
        this.userStatus = UserStatus.BAN;
    }

    public UserDetailResponse toUserDetailDto() {
        return UserDetailResponse.builder()
                .id(this.getId())
                .email(this.email)
                .nickname(this.nickname)
                .createdDate(this.getCreatedDate())
                .modifiedDate(this.getModifiedDate())
                .userLevel(this.userLevel)
                .userStatus(this.userStatus)
                .build();
    }

    public UserInfoDto toUserInfoDto() {
        return UserInfoDto.builder()
                .email(this.getEmail())
                .nickname(this.nickname)
                .userLevel(this.userLevel)
                .build();
    }

    public UserWishTag toUserWishDto() {
        return UserWishTag.builder()
                .groupSize(this.wishGroupSize)
                .expectedSchedule(this.wishExpectedSchedule)
                .tagList(this.tags)
                .build();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    private String roles;

    public List<String> getRoleList() {
        if (this.roles == null) {
            return Collections.emptyList(); // 빈 목록 반환 또는 다른 처리 수행
        }
        return Arrays.asList(this.roles.split(","));
    }

    public void settingRoles(String role) {
        this.roles = role;
    }


    public boolean checkUserBanStatus() {
        return this.userStatus == UserStatus.BAN;
    }

    public void checkWishGroupSize(int size) {
        this.wishGroupSize = size;
    }

    public void checkWishSchedule(int day) {
        this.wishExpectedSchedule = day;
    }


    public void addWishTags(TagOption tagOption) {
        tags.add(tagOption);
    }

}
```
