package inf.questpartner.domain.users.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserProfileImg {

    @Id
    @GeneratedValue
    @Column(name = "USER_PROFILE_IMG_ID")
    private Long id;

    private String profileImgPath; // 프로필 사진 경로

}
