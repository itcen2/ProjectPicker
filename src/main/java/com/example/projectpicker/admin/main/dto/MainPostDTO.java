package com.example.projectpicker.admin.main.dto;

import com.example.projectpicker.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class MainPostDTO {

    private String postId;
    private String postTitle;
    private String postContent;
    private LocalDateTime create_date;
    private Boolean allow;

    public MainPostDTO(PostEntity postEntity){
        this.postId = postEntity.getPostId();
        this.postTitle = postEntity.getPostTitle();
        this.postContent = postEntity.getPostContent();
        this.create_date = postEntity.getCreateDate();
        this.allow = postEntity.isAllow();
    }
}
