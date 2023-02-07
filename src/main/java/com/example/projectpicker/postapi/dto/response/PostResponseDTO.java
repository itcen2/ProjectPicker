package com.example.projectpicker.postapi.dto.response;

import com.example.projectpicker.postapi.entity.HashTagEntity;
import com.example.projectpicker.postapi.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostResponseDTO {

    private String authorName;
    private String authorEmail;
    private String title;
    private String content;
    private List<String> hashTags;

    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime regDate;

    public PostResponseDTO(PostEntity entity){
        this.authorName = entity.getUserName();
        this.authorEmail = entity.getUserEmail();
        this.title = entity.getPostTitle();
        this.content = entity.getPostContent();
        this.regDate = entity.getCreateDate();
        this.hashTags = entity.getHashTags()
                .stream()
                .map(HashTagEntity::getTagName)
                .collect(Collectors.toList());

    }
}
