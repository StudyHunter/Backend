package inf.questpartner.domain.room.common;

import inf.questpartner.util.Constants;
import lombok.Getter;


/**
 * 방 섬네일 선택지
 */
@Getter
public enum RoomThumbnail {
    THUMB_CODING_VER1("THUMB_CODING_VER1", "코딩학습 테마 버전1", "키보드를 사용하는 모습을 담은 감성사진", Constants.THUMB_CODING_VER1),
    THUMB_CODING_VER2("THUMB_CODING_VER2","코딩학습 테마 버전2","컴퓨터를 사용하는 여성 모습을 담은 감성사진", Constants.THUMB_CODING_VER2),
    THUMB_MEETING_VER3("THUMB_MEETING_VER3", "그룹스터디 테마 버전1","다수의 참여자와 의견을 자유롭게 나누는 모습을 담은 감성사진", Constants.THUMB_MEETING_VER1),
    THUMB_BOOK_REVIEW_VER1("THUMB_BOOK_REVIEW_VER1", "북스터디 테마 버전1", "다양한 책에 관심을 갖고 집중하는 모습을 담은 감성사진", Constants.THUMB_BOOK_REVIEW_VER1),
    THUMB_BOOK_REVIEW_VER2("THUMB_BOOK_REVIEW_VER2", "북스터디 테마 버전2", "음악을 들으며 책에 집중하는 여성 모습을 담은 감성사진",Constants.THUMB_BOOK_REVIEW_VER2),
    THUMB_DESIGN_VER1("THUMB_DESIGN_VER1", "디자인학습 테마 버전1", "스케치 패드에 자신의 디자인을 담는 여성의 모습을 담은 감성사진", Constants.THUMB_DESIGN_VER1);

    private String code;
    private String thumbnailType;
    private String typeInfo;
    private String imgPath;

    RoomThumbnail(String code, String thumbnailType, String typeInfo, String imgPath) {
        this.code = code;
        this.thumbnailType = thumbnailType;
        this.typeInfo = typeInfo;
        this.imgPath = imgPath;
    }
}