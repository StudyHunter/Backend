package inf.questpartner.domain.studytree;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * (Quest와 Item은 n:m 관계)
 *   Quest에는 여러 Item을 담을 수 있다.
 *   또한 Item은 여러 Quest에 포함될 수 있다. 따라서 ManyToMany를 형성한다.
 *
 *
 *  (ManyToMany의 경우 정규화를 통해 1:N , N:1로 처리해야 한다.)
 *  따라서 중간테이블인 QuestReward라는 중간테이블을 생성해야 한다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Quest {

    @Id
    @GeneratedValue
    @Column(name = "QUEST_ID")
    private Long id;

    private String questTitle; //퀘스트 명
    private String description; // 설명

    @ManyToOne
    @JoinColumn(name = "STUDY_TREE_ID")
    private StudyTree studyTree;

//    @OneToMany(mappedBy = "quest", orphanRemoval = true)
//    private List<QuestReward> rewardList = new ArrayList<>();
//
//    public void addRewardItem(QuestReward reward) {
//        rewardList.add(reward);
//    }
}