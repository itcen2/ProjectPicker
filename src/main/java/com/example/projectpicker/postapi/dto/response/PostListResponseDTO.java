package com.example.projectpicker.postapi.dto.response;

import lombok.*;

import java.util.List;
//파일 설명: 게시물 리스트(목록) 조회 DTO
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostListResponseDTO {

    private int count;
    private PageResponseDTO pageInfo;
    private List<PostResponseDTO> posts; //서비스에서 사용


}
