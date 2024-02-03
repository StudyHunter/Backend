package inf.questpartner.domain.users.user;

import inf.questpartner.domain.room.RoomUser;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserBase;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.dto.users.FindUserResponse;
import inf.questpartner.dto.users.UserInfoDto;
import inf.questpartner.dto.users.UserWishTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends UserBase {

    private String nickname; // 닉네임

    private int studyTime; // 총 공부 시간

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private List<TagOption> tags = new ArrayList<>();

    private int wishGroupSize;
    private int wishExpectedSchedule;

    // 1:1 단방향
//    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    @JoinColumn(name = "USER_PROFILE_IMG_ID")
    private String profilePath;

    /**
     * 1 : 1 단방향
     * USER는 하나의 트리만 가질 수 있다.
     * 트리 또한 여러명의 유저가 함께 사용할 수 없다. 따라서 일대일 매핑으로 처리한다.
     */
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDY_TREE_ID")
    private StudyTree studyTree;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomUser> roomUserList = new ArrayList<>();

    @Builder
    public User(Long id, String email, String password,
                String nickname, String profilePath, int wishGroupSize, int wishExpectedSchedule, List<TagOption> tags) {

        super(id, email, password, UserLevel.UNAUTH);
        this.userStatus = UserStatus.NORMAL;
        this.studyTime = 0;
        this.nickname = nickname;
        this.profilePath = profilePath;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.tags = tags;
    }

    public void updateUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

//    public UserDetailResponse toUserDetailDto() {
//        return UserDetailResponse.builder()
//                .id(this.getId())
//                .email(this.email)
//                .nickname(this.nickname)
//                .createdDate(this.getCreatedDate())
//                .modifiedDate(this.getModifiedDate())
//                .userLevel(this.userLevel)
//                .userStatus(this.userStatus)
//                .build();
//    }


    public UserInfoDto toUserInfoDto() {
        return UserInfoDto.builder()
                .email(this.getEmail())
                .nickname(this.getNickname())
                .userLevel(this.userLevel)
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

    public void updateProfile(String profilePath) {
        this.profilePath = profilePath;
    }

    public void updateUserWishTag(int wishGroupSize, int wishExpectedSchedule, List<TagOption> tags) {
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
        this.tags = tags;
    }

    public void updateProfileImg(String profilePath) {
        this.profilePath = profilePath;
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

    //    public void createRoom(Room room) {
//        this.rooms.add(room);
//    }
    public boolean isBan() {
        return this.userStatus == UserStatus.BAN;
    }

    public void createStudyTree(StudyTree studyTree) {
        this.studyTree = studyTree;
    }
}

