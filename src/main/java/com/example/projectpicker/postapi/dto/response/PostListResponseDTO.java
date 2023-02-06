package com.example.projectpicker.postapi.dto.response;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostListResponseDTO {

    private int count;
    private List<PostResponseDTO> posts; //서비스에서 사용 예정


}
