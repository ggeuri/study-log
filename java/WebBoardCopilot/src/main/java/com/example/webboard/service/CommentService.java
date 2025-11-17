package com.example.webboard.service;

import com.example.webboard.domain.Comment;
import com.example.webboard.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentMapper commentMapper;
    
    @Transactional
    public Comment createComment(Long postId, Long authorId, Long parentId, String content) {
        Comment comment = Comment.builder()
                .postId(postId)
                .authorId(authorId)
                .parentId(parentId)
                .content(content)
                .isDeleted(false)
                .build();
        
        commentMapper.insert(comment);
        return comment;
    }
    
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(Long id) {
        return Optional.ofNullable(commentMapper.selectByIdWithAuthor(id));
    }
    
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.selectByPostIdActive(postId);
    }
    
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByParentId(Long parentId) {
        return commentMapper.selectByParentId(parentId);
    }
    
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByAuthorId(Long authorId) {
        return commentMapper.selectByAuthorId(authorId);
    }
    
    @Transactional
    public void updateComment(Long id, String content) {
        Comment comment = Comment.builder()
                .id(id)
                .content(content)
                .build();
        commentMapper.update(comment);
    }
    
    @Transactional
    public void deleteComment(Long id) {
        commentMapper.softDelete(id);
    }
    
    @Transactional(readOnly = true)
    public long getCommentCountByPostId(Long postId) {
        return commentMapper.countByPostId(postId);
    }
}
