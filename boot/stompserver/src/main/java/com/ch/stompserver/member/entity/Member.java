package com.ch.stompserver.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
  [Member Entity]
  - DB의 member 테이블과 매핑되는 JPA 엔티티 클래스
  - 클래스/필드에 붙은 어노테이션 정보로 테이블 컬럼이 연결됨
*/
@Table(name="member")
@Entity
@NoArgsConstructor
@Getter
public class Member {

    /*
      [PK] member_id
      - @Id: 기본키(PK)
      - @GeneratedValue(IDENTITY): DB의 AUTO_INCREMENT 같은 방식으로 PK 자동 생성
      - @Column(name="member_id"): DB 컬럼명과 필드명을 매핑
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    /*
      [일반 컬럼]
      - 어노테이션 없으면 기본 규칙으로 컬럼 생성/매핑됨(필드명 = 컬럼명)
    */
    @Column(name="homepage_id", length = 20, nullable = false)
    private String homepageId;
    @Column(name="password", length = 64, nullable = false)
    private String password;
    @Column(name="name", length = 20, nullable = false)
    private String name;

    public Member(String homepageId, String password, String name) {
        this.homepageId = homepageId;
        this.password = password;
        this.name = name;
    }
}