package com.example.projectpicker.postapi.dto.request;


import com.example.projectpicker.postapi.entity.PostEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostCreateRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

//    @NotBlank
    private String userName;

//    @NotBlank
    private String userEmail;

//    private boolean allow;

    private List<String> hashTags;

    public PostEntity toEntity() {
        return PostEntity.builder()
                .postTitle(this.title)
                .postContent(this.content)
//                .allow(this.allow)
                .createDate(LocalDateTime.now())
                .build();
    }


}
