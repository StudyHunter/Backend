package inf.questpartner.controller.dto;


import inf.questpartner.domain.room.common.tag.TagOption;
import lombok.Data;

@Data
public class RoomSearchCondition {

    private String title;
    private TagOption tagOption;
}
