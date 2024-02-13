package inf.questpartner.domain.users.user;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomUser;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserBase;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserProfileImg;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.dto.UserWishTag;
import inf.questpartner.dto.users.UserDetailResponse;
import inf.questpartner.dto.users.UserInfoDto;
import inf.questpartner.dto.users.UserListResponse;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private List<TagOption> tags = new ArrayList<>(); // 취향저장

    private int wishGroupSize; //원하는 조건 : 인원/기간
    private int wishExpectedSchedule;

    // 1:1 단방향
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_PROFILE_IMG_ID")
    private UserProfileImg profileImg;

    /**
     * 1 : 1 단방향
     *  USER는 하나의 트리만 가질 수 있다.
     *  트리 또한 여러명의 유저가 함께 사용할 수 없다. 따라서 일대일 매핑으로 처리한다.
     */
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDY_TREE_ID")
    private StudyTree studyTree;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomUser> roomUserList = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @Builder
    public User(Long id, String email, String password,
                UserLevel userLevel, String nickname, UserStatus userStatus) {
        super(id, email, password, userLevel);
        this.nickname = nickname;
        this.userLevel = userLevel;
        this.userStatus = userStatus;
    }

    @Builder(builderMethodName = "roomTest")
    public User(Long id, String email, String password,
                UserLevel userLevel, String nickname, UserStatus userStatus, int wishGroupSize, int wishExpectedSchedule) {
        super(id, email, password, userLevel);
        this.nickname = nickname;
        this.userLevel = userLevel;
        this.userStatus = userStatus;
        this.wishGroupSize = wishGroupSize;
        this.wishExpectedSchedule = wishExpectedSchedule;
    }

    public void updateUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
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

    public void createRoom(Room room) {
        this.rooms.add(room);
    }
}
