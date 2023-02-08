package com.example.projectpicker.post.dto.request;

// 게시판 수정 버튼 클릭시 요청 (클라이언트 -- > DB)

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PostModifyRequestDTO {

    @NotBlank
//    @Size(min = 2, max = 10)
    private String title; // 게시판 제목

    @NotBlank
    private String content; // 게시판 내용
}
