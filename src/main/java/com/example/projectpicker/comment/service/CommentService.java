package com.example.projectpicker.comment.service;

import com.example.projectpicker.comment.dto.CommentRequestDTO;
import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.comment.repository.CommentRepository;
import com.example.projectpicker.post.entity.PostEntity;
import com.example.projectpicker.post.repository.PostRepository;
import com.example.projectpicker.user.entity.UserEntity;
import com.example.projectpicker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public String commentSave(String userEmail, String postId, CommentRequestDTO commentRequestDTO) {
        UserEntity user = userRepository.findByEmail(userEmail);
        PostEntity post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패 - id : " + postId + " 의 게시물이 존재하지 않습니다." ));
        commentRequestDTO.setUserEntity(user);
        commentRequestDTO.setPostEntity(post);

        CommentEntity comment = commentRequestDTO.toEntity();
        commentRepository.save(comment);

        return commentRequestDTO.getCommentId();
    }
}
