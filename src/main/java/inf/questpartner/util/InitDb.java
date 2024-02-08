package inf.questpartner.util;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.UserListResponse;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
//import org.h2.engine.UserBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Profile("local")
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1(); // admin test
//        initService.dbInit2(); // room
//        initService.init3Db(); // room test user
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {

            for (int i = 0; i < 200; i++) {

                User user = User.builder()
                        .email("user" + i + "@naver.com")
                        .password("1234" + i)
//                        .userLevel(UserLevel.AUTH)
                        .nickname("dawn" + i)
//                        .userStatus(UserStatus.NORMAL)
                        .build();

                em.persist(user);
            }
        }


//        public void dbInit2() {
//            for (int i=0; i<4; i++) {
//                Room room = Room.builder()
//                        .author("user" + i)
//                        .tags(getRandomTags())
//                        .roomType(RoomType.PROJECT)
//                        .build();
//
//                em.persist(room);
//            }
//        }


//        public void init3Db() {
//            User user = User.roomTest()
//                    .email("user@gmail.com")
//                    .password("12341234")
//                    .userLevel(UserLevel.AUTH)
//                    .nickname("dduddi")
//                    .userStatus(UserStatus.NORMAL)
//                    .wishGroupSize(3)
//                    .wishExpectedSchedule(7)
//                    .build();
//
//            em.persist(user);
//
//            List<TagOption> tags = getRandomTags();
//            for (TagOption tag : tags) {
//                user.addWishTags(tag);
//            }
//
//            user.checkWishGroupSize(2);
//            user.checkWishSchedule(4);
//
//        }
//    }

        private static List<TagOption> getRandomTags() {
            List<TagOption> allTags = Arrays.asList(TagOption.values());
            List<TagOption> randomTags = new ArrayList<>();

            // 모든 태그를 복사하여 새로운 리스트 생성
            List<TagOption> allTagsCopy = new ArrayList<>(allTags);

            // 무작위로 섞음
            Collections.shuffle(allTagsCopy);

            // 무작위로 선택할 태그의 개수 (여기서는 4개)
            int numberOfTagsToSelect = 4;

            // 무작위로 선택된 태그를 가져옴
            for (int i = 0; i < numberOfTagsToSelect && i < allTagsCopy.size(); i++) {
                randomTags.add(allTagsCopy.get(i));
            }

            return randomTags;
        }


    }
}
