package com.ch.noticeapp.notice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
 * [역할]
 * - Notice 엔티티: notice 테이블 1행(Row) ↔ 자바 객체 1개를 매핑
 * - JPA는 엔티티를 통해 CRUD 처리(ORM)
 *
 * [MyBatis vs JPA]
 * - MyBatis: SQL 직접 작성 + 결과를 DTO로 매핑(SQL Mapper)
 * - JPA: Entity(객체) ↔ Table 매핑으로 CRUD 처리(ORM)
 */

@Entity // JPA 관리 대상(엔티티)
@Table(name = "notice") // 매핑 테이블 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본생성자 필수 + 외부 new 제한
@Getter // 엔티티는 Setter 최소화(변경은 메서드로 통제, 요청/응답은 DTO 권장)
public class Notice {

    /* ======================
     * PK 매핑
     * ====================== */
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "notice_id") // notice_id ↔ noticeId
    private Long noticeId;

    /* ======================
     * 일반 컬럼
     * ====================== */
    @Column(name = "title", length = 100) // varchar(100)
    private String title;

    @Column(name = "writer", length = 20) // varchar(20)
    private String writer;

    @Lob
    // 큰 본문: String + @Lob => CLOB 성격
    @Column(name = "content", columnDefinition = "text")
    // MySQL에서 긴 문자열을 TEXT로 저장하도록 명시
    private String content;

    /* ======================
     * DB가 자동 채우는 컬럼
     * ====================== */
    @Column(name = "regdate", insertable = false, updatable = false)
    // DB default(now()) 같은 값이 들어가고,
    // JPA가 INSERT/UPDATE에 이 컬럼을 포함하지 않게 설정
    private LocalDateTime regdate;

    @Column(name = "hit")
    private int hit;

    /* ======================
     * 도메인 메서드(상태 변경을 통제)
     * ====================== */
    // 등록용 생성자(필수값만 받기)
    public Notice(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    // 수정은 Setter 대신 메서드로 통제
    public void update(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    // 조회수 증가 로직
    public void increaseHit() {
        this.hit += 1;
    }
}