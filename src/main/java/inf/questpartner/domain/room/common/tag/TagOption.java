package inf.questpartner.domain.room.common.tag;

import lombok.Getter;

/**
 * 스터디 방을 조회할 때 키워드 나열
 */
@Getter
public enum TagOption {
    /**
     * 관심 분야
     */

    AI("인공지능"),
    DEEPLEARNING("딥러닝"),
    CODINGTEST("코딩테스트"),
    ALGORITHM("알고리즘"),
    DEVELOP("개발"),
    INTERVIEW("면접준비"),
    STARTUP("창업"),
    DATA("데이터과학"),

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
    BOOKREVIEW("책 읽기"),
    TEAMPROJECT("팀 프로젝트 활동"),
    CODEREVIEW("코드 리뷰");

    private String viewName; // 태그 명칭

    TagOption(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
