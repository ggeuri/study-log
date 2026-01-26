package com.ch.stompserver.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberErrorCode {

    //enum안에는 문자,숫자,논리값 뿐 아니라 객체자료형도 모아놓을 수 있따
    MEMBER_NOT_FOUND("회원 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    MEMBER_CREATE_FAIL("회원 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_UPDATE_FAIL("회원 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_DELETE_FAIL("회원을 삭제할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    MemberErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    //JSON응답이나 로그 등에 사용할 에러코드 문자열
    public String getCode(){
        return this.name(); // NAME = NOTICE_NOT_FOUND같은거
    }


}
