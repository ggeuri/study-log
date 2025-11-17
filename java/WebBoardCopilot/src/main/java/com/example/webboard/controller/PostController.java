package com.example.webboard.controller;

import com.example.webboard.domain.Post;
import com.example.webboard.domain.User;
import com.example.webboard.dto.PostRequest;
import com.example.webboard.dto.PostResponse;
import com.example.webboard.service.PostService;
import com.example.webboard.service.CommentService;
import com.example.webboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    
    @GetMapping
    public String listPosts(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        List<Post> posts = postService.getPostsPaginated(page, pageSize);
        long totalCount = postService.getTotalPostCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", totalCount);
        
        return "posts/list";
    }
    
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostByIdAndIncreaseViews(id);
        
        if (post == null || post.getIsDeleted()) {
            return "redirect:/posts";
        }
        
        List comments = commentService.getCommentsByPostId(id);
        
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        
        return "posts/view";
    }
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("postRequest", new PostRequest());
        return "posts/create";
    }
    
    @PostMapping
    public String createPost(@ModelAttribute PostRequest request, 
                            Authentication authentication,
                            RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            Post post = postService.createPost(request.getTitle(), request.getContent(), user.getId());
            
            redirectAttributes.addFlashAttribute("message", "게시글이 작성되었습니다.");
            return "redirect:/posts/" + post.getId();
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "게시글 작성 중 오류가 발생했습니다.");
            return "redirect:/posts/create";
        }
    }
    
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, Authentication authentication) {
        Post post = postService.getPostById(id).orElse(null);
        
        if (post == null || !post.getAuthorId().equals(authentication.getName())) {
            return "redirect:/posts";
        }
        
        model.addAttribute("post", post);
        model.addAttribute("postRequest", PostRequest.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build());
        
        return "posts/edit";
    }
    
    @PutMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostRequest request,
                            Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            Post post = postService.getPostById(id).orElse(null);
            
            if (post == null) {
                return "redirect:/posts";
            }
            
            // 권한 체크 (작성자만 수정 가능)
            User user = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            if (!post.getAuthorId().equals(user.getId())) {
                redirectAttributes.addFlashAttribute("error", "수정 권한이 없습니다.");
                return "redirect:/posts/" + id;
            }
            
            postService.updatePost(id, request.getTitle(), request.getContent());
            redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
            return "redirect:/posts/" + id;
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "게시글 수정 중 오류가 발생했습니다.");
            return "redirect:/posts/" + id + "/edit";
        }
    }
    
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id, Authentication authentication, 
                            RedirectAttributes redirectAttributes) {
        try {
            Post post = postService.getPostById(id).orElse(null);
            
            if (post == null) {
                return "redirect:/posts";
            }
            
            // 권한 체크
            User user = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            if (!post.getAuthorId().equals(user.getId())) {
                redirectAttributes.addFlashAttribute("error", "삭제 권한이 없습니다.");
                return "redirect:/posts/" + id;
            }
            
            postService.deletePost(id);
            redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
            return "redirect:/posts";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "게시글 삭제 중 오류가 발생했습니다.");
            return "redirect:/posts/" + id;
        }
    }
}
