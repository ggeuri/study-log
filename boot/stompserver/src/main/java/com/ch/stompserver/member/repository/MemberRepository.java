package com.ch.stompserver.member.repository;

import com.ch.stompserver.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //JpaPository 인터페이스에는 필수덕인 find~ findBy등을 지원하기는 하지만 아주 필수적인 멤버필드 대해서만 한정적.
    // 나머지 필드들에 대한 메서드는 개발자가 정의
    Optional<Member> findByHomepageId(String homepageId);

}
