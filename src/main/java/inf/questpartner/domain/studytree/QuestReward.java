package inf.questpartner.domain.studytree;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QuestReward {

    @Id
    @GeneratedValue
    @Column(name = "QUEST_REWARD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDY_TREE_ID")
    private StudyTree studyTree;

    @ManyToOne
    @JoinColumn(name = "QUEST_ID")
    private Quest quest;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Builder
    public QuestReward(Quest quest, Item item) {
        this.quest = quest;
        this.item = item;
    }



}
