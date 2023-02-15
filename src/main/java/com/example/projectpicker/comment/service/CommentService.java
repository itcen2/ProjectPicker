package com.example.projectpicker.comment.service;

import com.example.projectpicker.comment.dto.CommentModifyRequestDTO;
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
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 댓글 생성
    @Transactional
    public void commentSave(String postId, CommentRequestDTO commentRequestDTO, String userId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패 - id : " + postId + " 의 게시물이 존재하지 않습니다." ));
//        commentRequestDTO.setUserEntity(user);
//        commentRequestDTO.setPostEntity(post);
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("사용자를 찾을 수 없습니다." ));

        CommentEntity comment = commentRequestDTO.toEntity(user, post);
        commentRepository.save(comment);

    }

    // 댓글 수정
    @Transactional
    public void update(String id, CommentModifyRequestDTO dto) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
//        comment.setComment(dto.getComment());
        commentRepository.save(dto.toEntity(comment));
    }


    // 댓글 삭제
    @Transactional
    public void delete(String id) {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }
}