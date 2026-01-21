package com.ch.noticeapp.notice.dto.response;

import com.ch.noticeapp.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/*
 * [왜 ResponseNotice를 만드나?]
 * - JPA를 쓰면 DB 테이블과 1:1로 매핑되는 Entity(Notice)가 필요함
 * - Entity는 "DB 영속성/도메인 상태" 용도라서 컨트롤러 응답(JSON)으로 바로 쓰는 걸 피하는 게 보통 안전함
 *   (필드 노출/지연로딩/양방향 관계 등으로 사이드이펙트 가능)
 *
 * [이름 충돌 해결]
 * - 기존에 MyBatis에서 Notice라는 DTO 이름을 이미 쓰고 있었음
 * - JPA에서 Entity 이름도 Notice로 가는 경우가 많아서(관례)
 *   "응답용 DTO"는 ResponseNotice 같은 별도 이름으로 분리해서 사용
 *
 * 결론:
 * - Entity(Notice) = DB 매핑/도메인
 * - ResponseNotice = 클라이언트로 내려주는 응답 전용 DTO
 */
@Builder
@Getter
public class ResponseNotice {

    private Long noticeId;
    private String title;
    private String writer;
    private String content;
    private Integer hit;
    private LocalDateTime regdate;

    // Entity -> Response DTO 변환(복사)
    public static ResponseNotice from(Notice notice) {
        return ResponseNotice.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .writer(notice.getWriter())
                .content(notice.getContent())
                .hit(notice.getHit())
                .regdate(notice.getRegdate())
                .build();
    }
}