package com.example.webboard.service;

import com.example.webboard.domain.User;
import com.example.webboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public User registerUser(String email, String username, String plainPassword) {
        // 이메일/사용자명 중복 검사
        if (userMapper.selectByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (userMapper.selectByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }
        
        // 비밀번호 암호화
        String hashedPassword = passwordEncoder.encode(plainPassword);
        
        User user = User.builder()
                .email(email)
                .username(username)
                .passwordHash(hashedPassword)
                .role("USER")
                .status("ACTIVE")
                .build();
        
        userMapper.insert(user);
        return user;
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }
    
    @Transactional
    public void updateLastLogin(Long userId) {
        userMapper.updateLastLogin(userId);
    }
    
    @Transactional
    public void updateUser(User user) {
        userMapper.update(user);
    }
}
