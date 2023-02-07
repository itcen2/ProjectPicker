package com.example.projectpicker.postapi.dto.response;

import com.example.projectpicker.postapi.dto.request.PageRequestDTO;
import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostListResponseDTO {

    private int count;
    private PageResponseDTO pageInfo;
    private List<PostResponseDTO> posts;
}
