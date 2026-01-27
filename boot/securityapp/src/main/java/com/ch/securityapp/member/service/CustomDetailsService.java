package com.ch.securityapp.member.service;

import com.ch.securityapp.member.dto.CustomUserDetails;
import com.ch.securityapp.member.entity.Member;
import com.ch.securityapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//스프링 요청처리흐름에서(필터체인) 사용자id조회하여 데이터베이스로부터 사용자정보가져오는역할수행.
//서비스 커스텀해보기
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    private  final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String homepageId) throws UsernameNotFoundException {
        log.debug("[서비스에서출력해보자~~~~~~~~~~~~~~~~~~~{}",homepageId);

        //일단 회원 ID이용해서 회원정보 가져옴. UserDetails라는 일종의 DTO에 담음
        // PasswordEncode가 UserDetails의 정보 비번 비교함(Provider가 수행)
        // 인증성공하면 Authentication 토큰에 성공정보 채워짐 -> UPA Filter에 반환 -> 성공이면SecurityContext에 성공정보저장+세션저장 +SuccessHandler호출 -> 이후 Controller에 요청전달
        Member member = memberRepository.findByHomepageId(homepageId).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원"));

        UserDetails ud = new CustomUserDetails(member);

        return ud;
    }
}
