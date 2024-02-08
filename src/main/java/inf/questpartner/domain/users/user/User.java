package inf.questpartner.domain.users.user;

import inf.questpartner.domain.room.common.tag.TagOption;
import com.fasterxml.jackson.annotation.JsonIgnore;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserBase;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserProfileImg;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.dto.UserWishTag;
import inf.questpartner.dto.users.UserDetailResponse;
import inf.questpartner.dto.users.UserInfoDto;
import inf.questpartner.dto.users.FindUserResponse;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

        super(id, email, password, UserLevel.UNAUTH);
        this.userStatus = UserStatus.NORMAL;
        this.studyTime = 0;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.tags = tags;
    }
    /**
     * 1 : 1 단방향
     *  USER는 하나의 트리만 가질 수 있다.
     *  트리 또한 여러명의 유저가 함께 사용할 수 없다. 따라서 일대일 매핑으로 처리한다.
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
                .createdDate(this.getCreatedDate())
                .modifiedDate(this.getModifiedDate())
                .userLevel(this.userLevel)
                .userStatus(this.userStatus)
                .build();
    }

    public UserInfoDto toUserInfoDto() {
        return UserInfoDto.builder()
                .email(this.getEmail())
                .nickname(this.getNickname())
                .userLevel(this.userLevel)
                .studyTime(this.studyTime)
                .studyTree(this.studyTree)
                .wishGroupSize(this.wishGroupSize)
                .wishExpectedSchedule(this.wishExpectedSchedule)
                .tags(this.tags)
                .build();
    }
    public FindUserResponse toFindUserDto() {
        return FindUserResponse.builder()
                .email(this.getEmail())
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

    // 관리자 권한으로 회원 BAN 처리하기 위한 로직
    public void updateUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void updateUserWishTag(int wishGroupSize, int wishExpectedSchedule, List<TagOption> tags) {
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.tags = tags;
    }

    public void updateProfileImg(UserProfileImg profileImg) {
        this.profileImg = profileImg;
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

    public void updateUserLevel() {
        this.userLevel = UserLevel.AUTH;
    }

    public boolean isBan() {
        return this.userStatus == UserStatus.BAN;
    }

    public void createStudyTree(StudyTree studyTree) {
        this.studyTree = studyTree;
    }
}
