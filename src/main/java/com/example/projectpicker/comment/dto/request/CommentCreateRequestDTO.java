//package com.example.projectpicker.comment.dto.request;
//
//
//import com.example.projectpicker.comment.entity.CommentEntity;
//import com.example.projectpicker.post.entity.PostEntity;
//import com.example.projectpicker.user.entity.UserEntity;
//import lombok.*;
//
//import javax.validation.constraints.NotBlank;
//
//// 댓글 생성 DTO
//@Getter @Setter @ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//@Builder
//public class CommentCreateRequestDTO {
//    @NotBlank
//    private String comment; // 댓글 내용
//    private PostEntity postEntity; // 게시판 엔티티
//    private UserEntity userEntity; // 회원 엔티티
//
//    public CommentEntity toCommentEntity(){
//        return CommentEntity.builder()
//                .comment(this.comment) // 클라이언트가 입력한 comment를 comment 엔티티에 comment로
//                .postEntity(this.postEntity)
//                .userEntity(this.userEntity)
//                .build();
//    }
//
//}
