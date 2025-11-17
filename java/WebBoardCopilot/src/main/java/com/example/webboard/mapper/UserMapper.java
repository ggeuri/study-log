package com.example.webboard.mapper;

import com.example.webboard.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // Create
    void insert(User user);
    
    // Read
    User selectById(Long id);
    Optional<User> selectByEmail(String email);
    Optional<User> selectByUsername(String username);
    
    // Update
    void update(User user);
    void updateLastLogin(Long userId);
    
    // Delete
    void delete(Long id);
}
