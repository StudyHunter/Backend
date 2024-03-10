package inf.questpartner.domain.room.common;

import lombok.Getter;

/**
 * 모임방 종류로 STUDY(스터디), PROJECT(팀프로젝트)가 있다.
 */
@Getter
public enum RoomType {
    STUDY("STUDY", "스터디"),
    PROJECT("PROJECT", "팀프로젝트");

    private String code;
    private String roomType;

    RoomType(String code, String roomType) {
        this.code = code;
        this.roomType = roomType;
    }
}