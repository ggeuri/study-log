package com.ch.stompserver.member.exception;

public class MemberException extends RuntimeException{
        //미리 만들어놓은 예외코드 객체 사용하기
        private final MemberErrorCode errorCode; //에러 메시지뿐만 아니라 에러코드도 이미 포함

        public  MemberException(MemberErrorCode errorCode){
            super(errorCode.getMessage()); // 한글메시지 "해당 글을 찾을 수 없습니다" 등
            this.errorCode = errorCode;
        }

        public  MemberErrorCode getErrorCode(){
            return  errorCode;
        }
    }

