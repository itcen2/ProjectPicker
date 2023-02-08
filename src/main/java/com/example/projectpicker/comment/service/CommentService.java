package com.example.projectpicker.comment.service;

import com.example.projectpicker.comment.dto.request.CommentCreateRequestDTO;
import com.example.projectpicker.comment.dto.response.CommentListResponseDTO;
import com.example.projectpicker.comment.dto.response.CommentResponseDTO;
import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.comment.repository.CommentRepository;
import com.example.projectpicker.post.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 목록 조회
    public CommentListResponseDTO retrieve(PostEntity postEntity){
        String postId = postEntity.getPostId();
        List<CommentEntity> entityList = commentRepository.findByComment(postId);

        List<CommentResponseDTO> dtoList = entityList.stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
        return CommentListResponseDTO.builder()
                .comments(dtoList)
                .build();
    }

    // 댓글 등록
    public CommentListResponseDTO create(final CommentCreateRequestDTO commentCreateRequestDTO,
                                         final PostEntity postEntity)throws RuntimeException{
        String postId = postEntity.getPostId();
        CommentEntity comment = commentCreateRequestDTO.toCommentEntity();

        commentRepository.save(comment);
        log.info("{} - 댓글이 등록되었습니다.", commentCreateRequestDTO.getComment());
        return retrieve(postEntity);
    }

    //댓글 삭제

    public CommentListResponseDTO delete(final String id, final PostEntity postEntity){
        String postId = postEntity.getPostId();

        try{
            commentRepository.deleteById(id);
        }catch (Exception e){
            log.error("ID가 존재하지 않아 삭제에 실패했습니다. - ID : {}, ERR : {}", id, e.getMessage() );
            throw new RuntimeException("ID가 존재하지 않아 삭제에 실패했습니다.");
        }
        return retrieve(postEntity);
    }



}
