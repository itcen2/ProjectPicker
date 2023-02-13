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

    /**
     * 댓글(comment)
     */

    private String commentId; // 댓글 식별 아이디(코드)
   private String comment; // 댓글 내용
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime createAt; // 댓글 생성 시간
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime modifyAt; // 댓글 수정 시간

    /**
     * 회원(user)
     */
    private String userName; // 회원 이름
   private String userEmail; // 회원 이메일

    /**
     * 게시글(post)
     */
    private String postId; // 게시글 식별 아이디(코드)


    /**
     * DTO <-- Entity
     */
    public CommentResponseDTO(CommentEntity commentEntity) {
       this.commentId = commentEntity.getCommentId();
       this.comment = commentEntity.getComment();
       this.createAt = commentEntity.getCreateAt();
       this.modifyAt = commentEntity.getModifyAt();
       this.userName = commentEntity.getUserEntity().getUserName();
       this.userEmail = commentEntity.getUserEntity().getUserEmail();
       this.postId = commentEntity.getPostEntity().getPostId();
   }
}
