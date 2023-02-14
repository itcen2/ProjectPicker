package com.example.projectpicker.post.dto.response;

import com.example.projectpicker.comment.dto.CommentRequestDTO;
import com.example.projectpicker.comment.dto.CommentResponseDTO;
import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostCommentDetailResponseDTO extends PostResponseDTO{

    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime modDate;

    private List<CommentResponseDTO> comment;
    public PostCommentDetailResponseDTO(PostEntity entity, List<CommentEntity> commentEntity) {
        super(entity);
        this.modDate = entity.getModifyDate();
        this.comment = commentEntity.stream().map(CommentResponseDTO::new).collect(Collectors.toList());

    }
}