package com.example.webboard.mapper;

import com.example.webboard.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    // Create
    void insert(Post post);
    
    // Read
    Post selectById(Long id);
    Optional<Post> selectByIdWithAuthor(Long id);
    List<Post> selectAll();
    List<Post> selectAllActive();
    List<Post> selectByAuthorId(Long authorId);
    List<Post> selectAllPaginated(int offset, int limit);
    
    // Update
    void update(Post post);
    void incrementViewCount(Long postId);
    
    // Delete (soft delete)
    void softDelete(Long id);
    
    // Count
    long countAll();
    long countActive();
}
