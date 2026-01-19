package com.ch.noticeapp.notice.exception;

public class NoticeException extends  RuntimeException{
    //미리 만들어놓은 예외코드 객체 사용하기
    private final NoticeErrorCode errorCode; //에러 메시지뿐만 아니라 에러코드도 이미 포함

    public  NoticeException(NoticeErrorCode errorCode){
        super(errorCode.getMessage()); // 한글메시지 "해당 글을 찾을 수 없습니다" 등
        this.errorCode = errorCode;
    }

    public  NoticeErrorCode getErrorCode(){
        return  errorCode;
    }
}
