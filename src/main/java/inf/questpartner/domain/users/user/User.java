package inf.questpartner.domain.users.user;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.studytree.StudyTree;
import inf.questpartner.domain.users.common.UserBase;
import inf.questpartner.domain.users.common.UserProfileImg;
import inf.questpartner.domain.users.common.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;

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


    @OneToMany(mappedBy = "USERS", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();


}
