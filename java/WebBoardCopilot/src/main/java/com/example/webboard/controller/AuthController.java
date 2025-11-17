package com.example.webboard.controller;

import com.example.webboard.domain.User;
import com.example.webboard.dto.SignupRequest;
import com.example.webboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "auth/signup";
    }
    
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request, Model model, RedirectAttributes redirectAttributes) {
        try {
            // 비밀번호 확인
            if (!request.getPassword().equals(request.getPasswordConfirm())) {
                model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
                return "auth/signup";
            }
            
            // 사용자 등록
            User user = userService.registerUser(request.getEmail(), request.getUsername(), request.getPassword());
            
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "redirect:/auth/login";
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/signup";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다.");
            return "auth/signup";
        }
    }
    
    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, 
                       RedirectAttributes redirectAttributes) {
        try {
            // 사용자 조회
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            // 로그인 시도
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            userService.updateLastLogin(user.getId());
            
            return "redirect:/posts";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "로그인 정보가 올바르지 않습니다.");
            return "redirect:/auth/login";
        }
    }
    
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
