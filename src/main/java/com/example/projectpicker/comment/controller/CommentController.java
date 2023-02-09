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
        commentService.commentSave(postId, dto);
//        return ResponseEntity.ok(commentService.commentSave(dto.getUserEntity().getUserEmail(), postId, dto));
    }

    // 댓글 수정
    @PutMapping({"/posts/{id}/comments/{ids}"})
    public ResponseEntity update(@PathVariable String ids, @RequestBody CommentRequestDTO dto) {
        commentService.update(ids, dto);
        return ResponseEntity.ok(ids);
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{id}/comments/{ids}")
    public ResponseEntity delete(@PathVariable String ids) {
        commentService.delete(ids);
        return ResponseEntity.ok(ids);
    }
}
