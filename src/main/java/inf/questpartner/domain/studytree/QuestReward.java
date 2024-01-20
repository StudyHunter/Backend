package inf.questpartner.domain.studytree;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QuestReward {  //아이템에서 본인이 획득한 것 item과 Quest 사이의 테이블
    @Id
    @GeneratedValue
    @Column(name = "QUEST_REWARD_ID")
    private Long id;

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