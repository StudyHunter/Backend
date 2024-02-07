package inf.questpartner.domain.users.common;

import lombok.Getter;

import static inf.questpartner.util.constant.Constants.*;


@Getter
public enum UserProfileImg {

    IMG_FINN("IMG_FINN", "캐릭터 핀 이미지", PROFILE_FINN),
    IMG_JIJI("IMG_JIJI", "캐릭터 고양이 지지 이미지", PROFILE_JIJI),
    IMG_KIKI("IMG_KIKI", "캐릭터 키키 이미지", PROFILE_KIKI),
    IMG_POCHACO("IMG_POCHACO", "캐릭터 포차코 이미지", PROFILE_POCHACO),
    IMG_WE_BARE("IMG_WE_BARE", "캐릭터 위베어 이미지", PROFILE_WE_BARE);

    private String code;
    private String typeInfo;
    private String imgPath;

    UserProfileImg(String code, String typeInfo, String imgPath) {
        this.code = code;
        this.typeInfo = typeInfo;
        this.imgPath = imgPath;
    }



}
