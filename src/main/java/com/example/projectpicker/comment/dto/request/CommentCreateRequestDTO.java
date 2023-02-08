package com.example.projectpicker.comment.dto.request;

import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.postapi.entity.PostEntity;
import com.example.projectpicker.user.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentCreateRequestDTO {
    @NotBlank
    private String comment;
    private PostEntity postEntity;
    private UserEntity userEntity;

    public CommentEntity toCommentEntity() {
        return CommentEntity.builder()
                .comment(this.comment)
                .userEntity(this.userEntity)
                .comments(this.postEntity)
                .build();
    }
}
