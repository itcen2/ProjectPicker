package com.example.projectpicker.admin.main.dto;

import com.example.projectpicker.post.entity.HashTagEntity;
import com.example.projectpicker.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class MainPostDTO {

    private String postId; // 게시글 식별 아이디(코드)
    private String postTitle; // 게시글 제목
    private String postContent; // 게시글 내용
    private String userEmail;
    private String userName;
    private LocalDateTime create_date; // 게시글 생성 날짜
    private Boolean allow; // 게시글 허용여부

    private List<String> hashTags;

    // (DTO <-- PostEntity)
    public MainPostDTO(PostEntity postEntity){
        this.postId = postEntity.getPostId();
        this.postTitle = postEntity.getPostTitle();
        this.postContent = postEntity.getPostContent();
        this.create_date = postEntity.getCreateDate();
        this.allow = postEntity.isAllow();
        this.userName = postEntity.getUserName();
        this.userEmail = postEntity.getUserEmail();
        this.hashTags = postEntity.getHashTags() // 게시글 해쉬태그
                .stream()
                .map(HashTagEntity::getTagName)
                .collect(Collectors.toList());
    }
}
