package com.ch.securityapp.member.dto;

//PasswordEncode는 이 클래스에 저장된 비밀번호 통해 검증. UserDetailsService가 회원정보 가져오는데 성공하면 직후 UserDetails에 회원정보 넣어줌

import com.ch.securityapp.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private  final Long memberId;
    private  final String homepageId;
    private  final String password;

    public CustomUserDetails(Member member) {
        this.memberId = member.getMemberId();
        this.homepageId = member.getHomepageId();
        this.password = member.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return homepageId;
    }
}
