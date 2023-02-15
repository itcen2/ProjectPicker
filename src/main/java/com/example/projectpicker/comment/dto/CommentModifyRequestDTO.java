package com.example.projectpicker.comment.dto;

import com.example.projectpicker.comment.entity.CommentEntity;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentModifyRequestDTO {
    private String comment;

    public CommentEntity toEntity(CommentEntity commentEntity) {
        return  CommentEntity.builder()
                .comment(comment)
                .commentId(commentEntity.getCommentId())
                .userEntity(commentEntity.getUserEntity())
                .userName(commentEntity.getUserName())
                .userEmail(commentEntity.getUserEmail())
                .postEntity(commentEntity.getPostEntity())
                .build();

    }
}