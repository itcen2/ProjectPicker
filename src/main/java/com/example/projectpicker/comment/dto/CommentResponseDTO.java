package com.example.projectpicker.comment.dto;

import com.example.projectpicker.comment.entity.CommentEntity;
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
   private LocalDateTime createAt;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime modifyAt;
   private String userName;
   private String userEmail;
   private String postId;

   public CommentResponseDTO(CommentEntity commentEntity) {
       this.commentId = commentEntity.getCommentId();
       this.comment = commentEntity.getComment();
       this.createAt = commentEntity.getCreateAt();
       this.modifyAt = commentEntity.getModifyAt();
       this.userName = commentEntity.getUserEntity().getUserName();
       this.userEmail = commentEntity.getUserEntity().getEmail();
       this.postId = commentEntity.getPostEntity().getPostId();
   }
}
