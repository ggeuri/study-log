package com.ch.noticeapp.notice.service;

import com.ch.noticeapp.notice.dto.request.RequestNotice;
import com.ch.noticeapp.notice.dto.response.ResponseNotice;
import com.ch.noticeapp.notice.entity.Notice;
import com.ch.noticeapp.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaNoticeService {

    // @RequiredArgsConstructor가 "생성자 주입"을 만들어주려면 필드는 반드시 final(또는 @NonNull) 이어야 함
    // final이 아니면 생성자가 안 만들어져서 noticeRepository가 null 될 수 있음
    private final NoticeRepository noticeRepository;

    // JPA 등록 핵심: Entity 생성 → save() 호출하면 INSERT + PK 자동 채움
    // 보통 Entity를 그대로 반환하지 말고 Response DTO로 변환해서 반환
    public ResponseNotice regist(RequestNotice requestNotice) {
        Notice notice = new Notice(requestNotice.getTitle(),requestNotice.getWriter(),requestNotice.getContent());
        Notice saved = noticeRepository.save(notice); //여기서 DB반영

        //절대로 응답에 사용될 객체로 Notice사용하면 안됨. 보안(DB와 직결된 애임) -> Entity안에 들어있는 데이터 중 필요한 것만 꺼내서 담을 responseNotice..
        //특정 객체 안의 데이터를 다른 데이터옮기기 - 빌더패턴
        
        return ResponseNotice.from(notice);

    }
}