package com.example.projectpicker.admin.main.dto;

import com.example.projectpicker.admin.main.entity.MainPostEntity;
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

    public MainPostDTO(MainPostEntity mainPostEntity){
        this.postId = mainPostEntity.getPostId();
        this.postTitle = mainPostEntity.getPostTitle();
        this.postContent = mainPostEntity.getPostContent();
        this.create_date = mainPostEntity.getCreateDate();
        this.allow = mainPostEntity.getAllow();
    }
}
