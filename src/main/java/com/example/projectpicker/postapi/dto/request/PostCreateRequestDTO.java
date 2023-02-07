package com.example.projectpicker.postapi.dto.request;


import com.example.projectpicker.postapi.entity.PostEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


// 게시판 생성 버튼 클릭시 (클라이언트 ---> DB)

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

public class PostCreateRequestDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String title; // 게시판 제목

    @NotBlank
    private String content; // 게시판 내용

    private List<String> hashTags;

    //DTO 를 엔티티로 ~
    public PostEntity toEntity() {
        return PostEntity.builder()
                .postTitle(this.title) // 클라이언트가 입력한 title 값을 엔티티
                .postContent(this.content)
                .build();
    }
}

