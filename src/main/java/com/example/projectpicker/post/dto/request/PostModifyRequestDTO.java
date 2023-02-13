package com.example.projectpicker.post.dto.request;

// 게시판 수정 버튼 클릭시 요청 (클라이언트 -- > DB)

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter @Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PostModifyRequestDTO {


    private String title; // 게시판 제목

    private String content; // 게시판 내용

    private boolean status; // 프로젝트 모집중 마감

    private List<String> hashTags; // 게시글 해쉬태그
}
