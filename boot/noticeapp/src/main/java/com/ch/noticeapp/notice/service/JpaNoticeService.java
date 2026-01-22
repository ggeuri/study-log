package com.ch.noticeapp.notice.service;

import com.ch.noticeapp.notice.dto.request.RequestNotice;
import com.ch.noticeapp.notice.dto.response.ResponseNotice;
import com.ch.noticeapp.notice.entity.Notice;
import com.ch.noticeapp.notice.exception.NoticeErrorCode;
import com.ch.noticeapp.notice.exception.NoticeException;
import com.ch.noticeapp.notice.repository.NoticeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaNoticeService {

    // [DI] EntityManager
    // - JPA 영속성 컨텍스트 제어(1차캐시/변경감지/flush 등)
    // - @PersistenceContext로 주입
    // - DB 반영 시점은 보통 flush/commit 때(즉시 INSERT/UPDATE 아닐 수 있음)
    @PersistenceContext
    private EntityManager em;

    // [DI] NoticeRepository
    // - @RequiredArgsConstructor: final 필드만 생성자 주입
    // - null 방지 + 주입/테스트 안정성
    private final NoticeRepository noticeRepository;

    // [등록] Request DTO → Entity 생성 → save() → Response DTO 반환
    // - Entity 그대로 반환하면 노출/결합/직렬화 이슈 가능 → DTO 권장
    @Transactional
    public ResponseNotice regist(RequestNotice requestNotice) {

        // [1] DTO → Entity (저장할 값으로 엔티티 생성)
        Notice notice = new Notice(
                requestNotice.getTitle(),
                requestNotice.getWriter(),
                requestNotice.getContent()
        );

        // [2] save(): 영속화
        // - INSERT는 트랜잭션 flush/commit 시점에 나갈 수 있음
        // - PK 채움 시점은 @Id 생성전략에 따라 달라짐
        Notice saved = noticeRepository.save(notice);

        // [3] refresh(): DB 값을 다시 읽어와 엔티티에 덮어씀
        // - 읽기 전에 flush가 동반될 수 있음
        em.refresh(saved);

        // [4] Entity → Response DTO (응답은 필요한 값만)
        return ResponseNotice.from(saved);
    }

    // [목록 조회]
    @Transactional(readOnly = true)
    // - readOnly=true: 조회 전용(불필요한 변경감지 비용 줄이는 용도)
    public List<ResponseNotice> getList(){

        // noticeId DESC로 조회 → Entity 리스트를 DTO 리스트로 변환
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"noticeId"))
                .stream()
                .map(ResponseNotice::from)
                .toList();
    }
    // [상세 조회] + 조회수 증가
    @Transactional
    public ResponseNotice getDetail(Long noticeId){
        // findById(): Optional 반환 → 없으면 예외(404 성격)
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(()-> new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND));
        // 조회수(hit) 증가: 더티체킹으로 flush/commit 시 UPDATE
        notice.increaseHit();
        return ResponseNotice.from(notice);

    }

    // [수정] 더티체킹으로 UPDATE 반영
    @Transactional
    public  ResponseNotice update(Long noticeId, RequestNotice request){
        // 없으면 예외 처리
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(()-> new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND));
        // 엔티티 값만 변경 → 트랜잭션 끝날 때(flush/commit) UPDATE 실행
        notice.update(request.getTitle(),request.getWriter(),request.getContent());

         return ResponseNotice.from(notice);   
    }

    // [삭제]
    @Transactional
    public void delete(Long noticeId){
        // 없으면 예외 처리 후 delete()
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(()-> new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND));
        noticeRepository.delete(notice);
    }

}