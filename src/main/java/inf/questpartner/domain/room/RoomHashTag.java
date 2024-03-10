package inf.questpartner.domain.room;

import inf.questpartner.domain.room.common.tag.TagOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoomHashTag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_hash_tag_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    private TagOption tagOption;

    @Builder
    public RoomHashTag(Room room, TagOption tagOption) {
        this.room = room;
        this.tagOption = tagOption;
    }

    public String getTagName() {
        return tagOption.getViewName();
    }
}
