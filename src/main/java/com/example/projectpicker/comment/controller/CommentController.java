package com.example.projectpicker.comment.controller;

import com.example.projectpicker.comment.dto.CommentRequestDTO;
import com.example.projectpicker.comment.service.CommentService;
import com.example.projectpicker.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projectpicker")
public class CommentController {
    private final CommentService commentService;

    private final PostService postService;

    // 댓글 생성
    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> commentSave(@PathVariable String postId, @RequestBody CommentRequestDTO dto) {
        commentService.commentSave(postId, dto);
        return ResponseEntity.ok().body(postService.getDetail(postId));
    }

    // 댓글 수정
    @PutMapping({"/posts/{id}/comments/{ids}"})
    public ResponseEntity update(@PathVariable String id, @PathVariable String ids, @RequestBody CommentRequestDTO dto) {
        commentService.update(ids, dto);
        return ResponseEntity.ok().body(postService.getDetail(id));
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{id}/comments/{ids}")
    public ResponseEntity delete(@PathVariable String id, @PathVariable String ids) {
        commentService.delete(ids);
        return ResponseEntity.ok().body(postService.getDetail(id));
    }
}