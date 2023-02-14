package com.example.projectpicker.post.dto.response;

import com.example.projectpicker.comment.dto.CommentResponseDTO;
import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

// 파일 설명: 수정 했을때 게시글 내부 정보 수정?
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponseDTO extends PostResponseDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime modifyDate; // 게시글 수정 시간


    private String content; // 게시글 내용
    private List<String> hashTags; // 게시글 해쉬태그
    private List<CommentResponseDTO> comments;

    public PostDetailResponseDTO(PostEntity postEntity){
        super(postEntity);
        this.modifyDate = postEntity.getModifyDate();
    }
}