package inf.questpartner.domain.studytree;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String itemName;
    private String itemImgPath; // 아이템 이미지

    @ManyToOne
    @JoinColumn(name = "STUDY_TREE_ID")
    private StudyTree studyTree;
}

/**
 * (고민중)
 * 아이템에 대한 이미지 테이블을 추가하려고 했는데
 * 생각해보니, 아이템에 대한 이미지가 고정값이라 필드값으로 넣었어요.
 */
