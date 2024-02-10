package inf.questpartner.domain.users.user;


import inf.questpartner.domain.room.common.tag.TagOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

//user와 tagOption으로 만들게 되면 처음에 가입할 때
// 등록되지않은 user에 대해서는 생성이 불가능하지 않을까요
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserWishHashTag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_wish_hash_tag_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TagOption tagOption;

    @Builder
    public UserWishHashTag(User user, TagOption tagOption) {
        this.user = user;
        this.tagOption = tagOption;
    }
}
