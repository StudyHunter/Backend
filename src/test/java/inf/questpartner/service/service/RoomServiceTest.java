package inf.questpartner.service.service;


import inf.questpartner.controller.dto.RoomSearchCondition;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.dto.room.res.ResRoomPreview;
import inf.questpartner.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class RoomServiceTest {


    @Autowired
    RoomService roomService;

    @Test
    @DisplayName("방제목으로 방 검색이 되는지?")
    public void test_sort() {
        RoomSearchCondition condition = new RoomSearchCondition();
        condition.setTitle("빠르게");

        Pageable pageable = PageRequest.of(0, 2);

        Page<ResRoomPreview> result = roomService.sort(condition, pageable);

        for (ResRoomPreview entity : result.getContent()) {
            log.info(entity.toString());
        }
    }

    @DisplayName("조건을 입력하지 않은 경우, 전체 조회")
    @Test
    public void test_sort_none() {
        RoomSearchCondition condition = new RoomSearchCondition();

        Pageable pageable = PageRequest.of(0, 10);

        Page<ResRoomPreview> result = roomService.sort(condition, pageable);

        for (ResRoomPreview room : result.getContent()) {
            log.info(room.toString());
        }
    }

    @DisplayName("태그로 검색하기")
    @Test
    public void test_sort_tag() {
        RoomSearchCondition condition = new RoomSearchCondition();
        condition.setTagOption(TagOption.AI);
        Pageable pageable = PageRequest.of(0, 2);

        Page<ResRoomPreview> result = roomService.sort(condition, pageable);

        for (ResRoomPreview entity : result.getContent()) {
            log.info(entity.toString());
        }
    }




}