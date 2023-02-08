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
        private String comment;


        public CommentEntity toEntity(UserEntity userEntity, PostEntity postEntity) {
            return  CommentEntity.builder()
                    .comment(comment)
                    .userEntity(userEntity)
                    .postEntity(postEntity)
                    .build();

        }
    }
