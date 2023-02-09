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
public class CommentRequestDTO {
        private String commentId;
        private String comment;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime createAt;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime modifyAt;
        private UserEntity userEntity;
        private PostEntity postEntity;

        public CommentEntity toEntity() {
            return  CommentEntity.builder()
                    .commentId(commentId)
                    .comment(comment)
                    .createAt(createAt)
                    .modifyAt(modifyAt)
                    .userEntity(userEntity)
                    .postEntity(postEntity)
                    .build();

        }
    }
