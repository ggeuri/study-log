package com.ch.stompserver.member;

import com.ch.stompserver.member.dto.request.MemberRequest;
import com.ch.stompserver.member.dto.response.MemberResponse;
import com.ch.stompserver.member.entity.Member;
import com.ch.stompserver.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

//로그인 요청 처리 컨트롤러
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;

    public AuthController(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "HIHIHIHIHIHI";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequest request, HttpServletRequest req) {
        log.debug("id = {}, pass = {}", request.getHomepageId(), request.getPassword());
        String homepageId = request.getHomepageId();
        String password = request.getPassword();
        ;

        //스프링 시큐리티는 데이터베이스와 넘겨받은 파라미터의 비교검증을 자동으로 해준다. 이때, 이러한 보안처리를 담당하는 객체가 AuthenticationManager이다.
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(homepageId, password));

            //인증성공하고나면 세션에 사용자 정보저장하고 응답정보로 필요한것을 디비에서 가져와야함
            SecurityContextHolder.getContext().setAuthentication(auth);
            HttpSession session = req.getSession();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            Member member = memberRepository.findByHomepageId(homepageId).orElse(null);

            return ResponseEntity.ok(new MemberResponse(true, member.getName()));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

    }

    //로그인한 사용자가 자신의 정보를 가져갈 수 있도록 요청 처리
    @GetMapping("/me")
    public ResponseEntity<?> myinfo(Authentication authentication){
        //스프링 시큐리티에 의한 로그인 처리 성공된 사람이라면, Authentication이 null이 아님 . 널이거나 인증안됐으면 진행불가
        if(authentication == null || !authentication.isAuthenticated()){
            return ResponseEntity.status(401).body("로그인이 필요한 서비스입니다.");
        }

        String homepageId = authentication.getName();
        Member member = memberRepository.findByHomepageId(homepageId).orElse(null);

        return ResponseEntity.ok(new MemberResponse(true,member.getName()));
    }
}
