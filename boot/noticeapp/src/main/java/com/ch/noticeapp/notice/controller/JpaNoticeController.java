package com.ch.noticeapp.notice.controller;

import com.ch.noticeapp.notice.dto.request.RequestNotice;
import com.ch.noticeapp.notice.dto.response.ResponseNotice;
import com.ch.noticeapp.notice.service.JpaNoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
//  Lombok이 "final 필드"를 받는 생성자를 자동 생성 → 생성자 주입 강제(= jpaNoticeService null 방지)
public class JpaNoticeController {

    private final JpaNoticeService jpaNoticeService;

    @PostMapping
    // @RequestBody: JSON → RequestNotice로 Jackson이 매핑
    public ResponseEntity<ResponseNotice> regist(@RequestBody RequestNotice request) {
        ResponseNotice created = jpaNoticeService.regist(request);
        return ResponseEntity.ok(created); // (학습용 OK) 보통 생성은 201 Created도 자주 씀
    }

    @GetMapping
    public ResponseEntity<List<ResponseNotice>> getList(){
        return ResponseEntity.ok(jpaNoticeService.getList());
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<ResponseNotice> update(@PathVariable Long noticeId, @RequestBody RequestNotice request) {
        return ResponseEntity.ok(jpaNoticeService.update(noticeId, request));
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> delete(@PathVariable Long noticeId){
        jpaNoticeService.delete(noticeId);
        return ResponseEntity.noContent().build();
    }



}