package inf.questpartner.domain.studytree;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "STUDY_TREE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudyTree {

    @Id
    @GeneratedValue
    @Column(name = "STUDY_TREE_ID")
    private Long id;

    /**
     * n : 1 양방향
     * (tree -> item) 트리에 아이템을 n개 담을 수 있다.
     * (tree -> quest) 트리에 퀘스트(목표)를 n개 세울 수 있다.
     */
    @OneToMany(mappedBy = "STUDY_TREE", orphanRemoval = true)
    private List<QuestReward> rewardList = new ArrayList<>();

    @OneToMany(mappedBy = "STUDY_TREE", orphanRemoval = true)
    private List<Quest> questList = new ArrayList<>();


}
