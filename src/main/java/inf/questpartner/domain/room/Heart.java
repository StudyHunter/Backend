package inf.questpartner.domain.room;

import inf.questpartner.domain.users.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Room에 대한 '좋아요' 버튼
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "hearts")
public class Heart {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "HEART_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    //heartId는 생성자가 필요없어서 @AllArgsConstructor 사용 하지 않음.
    public Heart(User user, Room room){
        this.user = user;
        this.room = room;
    }
}
