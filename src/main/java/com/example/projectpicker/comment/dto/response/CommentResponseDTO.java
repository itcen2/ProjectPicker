package com.example.projectpicker.comment.dto.response;

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
    private String userName;
    private String comment;

    @JsonFormat(pattern = "yyyy년 MM월 dd일 a hh시 mm분 ss초")
    private LocalDateTime createAt;

    public CommentResponseDTO(CommentEntity commentEntity) {
        this.userName = commentEntity.getUserEntity().getUserName();
        this.comment = commentEntity.getComment();
        this.createAt = commentEntity.getCreateAt();
    }
}
