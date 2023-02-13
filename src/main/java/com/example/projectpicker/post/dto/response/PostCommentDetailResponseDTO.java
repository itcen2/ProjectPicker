package com.example.projectpicker.post.dto.response;

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
public class PostCommentDetailResponseDTO extends PostDetailResponseDTO{


    private List<String> comment;
    public PostCommentDetailResponseDTO(PostEntity entity, List<CommentEntity> commentEntity) {
        super(entity);
        this.comment = commentEntity.stream()
                .map(CommentEntity::getComment)
                .collect(Collectors.toList());

    }
}