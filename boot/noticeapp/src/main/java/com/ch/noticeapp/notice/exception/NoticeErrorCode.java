package com.ch.noticeapp.notice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum NoticeErrorCode {

    //enum안에는 문자,숫자,논리값 뿐 아니라 객체자료형도 모아놓을 수 있따
    NOTICE_NOT_FOUND("해당 글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOTICE_CREATE_FAIL("글 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOTICE_UPDATE_FAIL("글 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOTICE_DELETE_FAIL("해당 글을 삭제할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    NoticeErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    //JSON응답이나 로그 등에 사용할 에러코드 문자열
    public String getCode(){
        return this.name(); // NAME = NOTICE_NOT_FOUND같은거
    }


}
