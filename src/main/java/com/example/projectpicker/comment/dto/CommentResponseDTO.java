package com.example.projectpicker.comment.dto;

import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.post.entity.PostEntity;
import com.example.projectpicker.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentResponseDTO {
    private String commentId;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifyAt;
    private String userName;
    private String userEmail;
    private String userId;

    public CommentResponseDTO(CommentEntity commentEntity) {
        this.commentId = commentEntity.getCommentId();
        this.comment = commentEntity.getComment();
        this.modifyAt = commentEntity.getModifyAt();
        this.userName = commentEntity.getUserName();
        this.userEmail = commentEntity.getUserEmail();
        this.userId = commentEntity.getUserEntity().getUserId();
    }
}