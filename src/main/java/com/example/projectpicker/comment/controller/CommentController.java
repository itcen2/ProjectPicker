package com.example.projectpicker.comment.controller;

import com.example.projectpicker.comment.dto.CommentRequestDTO;
import com.example.projectpicker.comment.entity.CommentEntity;
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
        CommentEntity commentEntity = commentService.commentSave(postId, dto);
        return ResponseEntity.ok().body("save");
    }
}
