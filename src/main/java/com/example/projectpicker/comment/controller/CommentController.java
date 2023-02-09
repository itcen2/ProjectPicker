package com.example.projectpicker.comment.controller;

import com.example.projectpicker.comment.dto.CommentRequestDTO;
import com.example.projectpicker.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projectpicker")
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> commentSave(@PathVariable String postId, @RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok(commentService.commentSave(dto.getUserEntity().getUserEmail(), postId, dto));
    }

    // 댓글 수정
    @PutMapping({"/posts/{id}/comments/{id}"})
    public ResponseEntity update(@PathVariable String id, @RequestBody CommentRequestDTO dto) {
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{id}/comments/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }
}
