package com.ch.stompserver.member.service;

import com.ch.stompserver.member.entity.Member;
import com.ch.stompserver.member.exception.MemberErrorCode;
import com.ch.stompserver.member.exception.MemberException;
import com.ch.stompserver.member.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {
    //회원정보 조회 실제적으로 담당하는 서비스 객체
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String homepageId) throws UsernameNotFoundException {

        Member member = memberRepository.findByHomepageId(homepageId).orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 흠 new User?
        return new User(member.getHomepageId(), member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE USER")));
    }
}
