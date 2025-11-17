package com.example.webboard.controller;

import com.example.webboard.domain.Comment;
import com.example.webboard.domain.User;
import com.example.webboard.dto.CommentRequest;
import com.example.webboard.service.CommentService;
import com.example.webboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;
    private final UserService userService;
    
    @PostMapping
    public String createComment(@PathVariable Long postId,
                               @ModelAttribute CommentRequest request,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            Comment comment = commentService.createComment(
                    postId, user.getId(), request.getParentId(), request.getContent()
            );
            
            redirectAttributes.addFlashAttribute("message", "댓글이 작성되었습니다.");
            return "redirect:/posts/" + postId;
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "댓글 작성 중 오류가 발생했습니다.");
            return "redirect:/posts/" + postId;
        }
    }
    
    @PutMapping("/{commentId}")
    public String updateComment(@PathVariable Long postId,
                               @PathVariable Long commentId,
                               @ModelAttribute CommentRequest request,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            Comment comment = commentService.getCommentById(commentId).orElse(null);
            
            if (comment == null) {
                return "redirect:/posts/" + postId;
            }
            
            // 권한 체크 (작성자만 수정 가능)
            User user = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            if (!comment.getAuthorId().equals(user.getId())) {
                redirectAttributes.addFlashAttribute("error", "수정 권한이 없습니다.");
                return "redirect:/posts/" + postId;
            }
            
            commentService.updateComment(commentId, request.getContent());
            redirectAttributes.addFlashAttribute("message", "댓글이 수정되었습니다.");
            return "redirect:/posts/" + postId;
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "댓글 수정 중 오류가 발생했습니다.");
            return "redirect:/posts/" + postId;
        }
    }
    
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long postId,
                               @PathVariable Long commentId,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            Comment comment = commentService.getCommentById(commentId).orElse(null);
            
            if (comment == null) {
                return "redirect:/posts/" + postId;
            }
            
            // 권한 체크
            User user = userService.findByUsername(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
            
            if (!comment.getAuthorId().equals(user.getId())) {
                redirectAttributes.addFlashAttribute("error", "삭제 권한이 없습니다.");
                return "redirect:/posts/" + postId;
            }
            
            commentService.deleteComment(commentId);
            redirectAttributes.addFlashAttribute("message", "댓글이 삭제되었습니다.");
            return "redirect:/posts/" + postId;
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "댓글 삭제 중 오류가 발생했습니다.");
            return "redirect:/posts/" + postId;
        }
    }
}
