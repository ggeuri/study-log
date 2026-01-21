package com.ch.noticeapp.notice.controller;

import com.ch.noticeapp.notice.dto.mybatis.Notice;
import com.ch.noticeapp.notice.service.MybatisNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController // ResponseBody명시할 필요없   음 ( // JAVA 클래스를 -> JSON으로 *JACKSON이)
//@RequestMapping("/api/notices")
public class MybatisNoticeController {

    private final MybatisNoticeService noticeService; //final붙이면 반드시 초기화하고 써야

    //    실수방지로 생성자주입 강제(인스턴스 존재해야 생성된다)
    public MybatisNoticeController(MybatisNoticeService noticeService) {
        this.noticeService = noticeService;
    }

    //글쓰기 요청 처리
    @PostMapping
    public ResponseEntity<Notice> regist(@RequestBody Notice notice){ //JSON을 -> JAVA클래스로 *JACKSON이

        Notice created  = noticeService.regist(notice);

        //created()매서드 안에는 클라이언트에게 등록된 자원의 위치를 알려주는 코드를 작성할 수 있음
        //return ResponseEntity.created(URI.create("/api/notices"+notice.getNoticeId())).body(notice); 이건 필수 아님. 위치알려주고싶을때쓰는거임
        //useGeneratedKeys="true" 덕분에 noticeService.regist(notice); 이후에는 noticeId가 채워져있다.
        return ResponseEntity.created(URI.create("/api/notices"+notice.getNoticeId())).body(created);
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> delete(@PathVariable Long noticeId) { //JSON을 -> JAVA클래스로 *JACKSON이

        log.debug("삭제할 글의 pk는 {}",noticeId);
        noticeService.delete(noticeId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<Notice> getContent(@PathVariable Long noticeId){
        return ResponseEntity.ok(noticeService.getContent(noticeId));
    }

    @GetMapping
    public ResponseEntity<List<Notice>> getList(){
        return ResponseEntity.ok(noticeService.getList());
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<Notice> update(@RequestBody Notice notice, @PathVariable Long noticeId) { //JSON을 -> JAVA클래스로 *JACKSON이
        notice.setNoticeId(noticeId); // 여기가 포인트.. notice에 PK값 안들어가있으니까 넣어주기
        return ResponseEntity.ok(noticeService.update(notice));
    }

    }

