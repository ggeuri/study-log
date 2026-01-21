package com.ch.noticeapp.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/*
 * RequestNotice = "요청 DTO"
 * - 컨트롤러에서 JSON 요청 바디를 받을 때 사용하는 객체
 * - 엔티티(Notice)랑 역할이 다름: 요청/응답은 DTO, DB 매핑은 Entity
 *
 * Setter를 안 두는 이유(선택):
 * - 값 변경을 막아 DTO를 더 안전하게 쓰려는 의도(불변/통제)
 * - 대신 Jackson이 역직렬화(JSON -> 객체)할 때 생성자/세터가 필요할 수 있음
 *   → 보통은 @NoArgsConstructor + setter, 또는 @Builder/@AllArgsConstructor 패턴을 씀
 *   (현재 코드는 "Jackson 설정/버전"에 따라 필드 주입이 안 될 수도 있음)
 *
 * Validation:
 * - @Valid(컨트롤러 파라미터)에 붙이면 아래 제약들이 자동 검증됨
 */

@Getter
public class RequestNotice {

    // @NotBlank: null, "", "   " 전부 불가
    // @Size: 길이 제한(여기서는 최대 100)
    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String writer;

    @NotBlank
    private String content;
}