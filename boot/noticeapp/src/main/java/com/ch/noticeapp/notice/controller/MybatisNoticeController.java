package com.ch.noticeapp.notice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//공지게시판 요청 처리하는 컨트롤러
@Controller
public class MybatisNoticeController {
    //글쓰기 요청 처리
    @PostMapping("/notice/regist")
    @ResponseBody
    public String regist(){
        return "글쓰기요청받음";
    }

}
