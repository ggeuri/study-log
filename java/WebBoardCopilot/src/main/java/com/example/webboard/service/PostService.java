package com.example.webboard.service;

import com.example.webboard.domain.Post;
import com.example.webboard.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostMapper postMapper;
    
    @Transactional
    public Post createPost(String title, String content, Long authorId) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .authorId(authorId)
                .viewCount(0)
                .isDeleted(false)
                .build();
        
        postMapper.insert(post);
        return post;
    }
    
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id) {
        return Optional.ofNullable(postMapper.selectByIdWithAuthor(id));
    }
    
    @Transactional
    public Post getPostByIdAndIncreaseViews(Long id) {
        postMapper.incrementViewCount(id);
        return postMapper.selectByIdWithAuthor(id);
    }
    
    @Transactional(readOnly = true)
    public List<Post> getAllActivePosts() {
        return postMapper.selectAllActive();
    }
    
    @Transactional(readOnly = true)
    public List<Post> getPostsByAuthorId(Long authorId) {
        return postMapper.selectByAuthorId(authorId);
    }
    
    @Transactional(readOnly = true)
    public List<Post> getPostsPaginated(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return postMapper.selectAllPaginated(offset, pageSize);
    }
    
    @Transactional
    public void updatePost(Long id, String title, String content) {
        Post post = Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        postMapper.update(post);
    }
    
    @Transactional
    public void deletePost(Long id) {
        postMapper.softDelete(id);
    }
    
    @Transactional(readOnly = true)
    public long getTotalPostCount() {
        return postMapper.countActive();
    }
}
