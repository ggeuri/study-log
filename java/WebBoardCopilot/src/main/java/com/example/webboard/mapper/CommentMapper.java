package com.example.webboard.mapper;

import com.example.webboard.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {
    // Create
    void insert(Comment comment);
    
    // Read
    Comment selectById(Long id);
    Optional<Comment> selectByIdWithAuthor(Long id);
    List<Comment> selectByPostId(Long postId);
    List<Comment> selectByPostIdActive(Long postId);
    List<Comment> selectByParentId(Long parentId);
    List<Comment> selectByAuthorId(Long authorId);
    
    // Update
    void update(Comment comment);
    
    // Delete (soft delete)
    void softDelete(Long id);
    
    // Count
    long countByPostId(Long postId);
}
