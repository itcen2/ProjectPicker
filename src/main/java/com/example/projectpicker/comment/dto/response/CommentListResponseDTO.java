package com.example.projectpicker.comment.dto.response;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentListResponseDTO {
    private String error;
    private List<CommentResponseDTO> comments;
}
