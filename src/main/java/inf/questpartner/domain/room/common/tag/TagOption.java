package inf.questpartner.domain.room.common.tag;

import lombok.Getter;

/**
 * 스터디 방을 조회할 때 키워드 나열
 */
@Getter
public enum TagOption {
    AI("인공지능"),
    DEEP_LEARNING("딥러닝"),
    CODING_TEST("코딩테스트"),
    ALGORITHM("알고리즘"),
    DEVELOP("개발"),
    INTERVIEW("면접준비"),
    STARTUP("창업"),
    DATA("데이터과학"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    PORTFOLIO("포트폴리오"),
    STUDY("스터디"),
    APP_DEVELOP("앱개발"),
    DEVOPS("데브옵스"),
    GAME("게임"),
    SYSTEM("시스템"),

    /**
     * 기술 스택
     */
    PYTHON("파이썬"),
    JAVA("자바"),
    JAVASCRIPT("자바스크립트"),
    NODEJS("노드"),
    REACT("리액트"),
    SPRING("스프링"),

    /**
     * 공부 유형
     */
    BOOK_REVIEW("책 읽기"),
    TEAM_PROJECT("팀 프로젝트 활동"),
    CODE_REVIEW("코드 리뷰");

    private String viewName; // 태그 명칭

    TagOption(String viewName) {
        this.viewName = viewName;
    }

}