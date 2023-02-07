package com.example.projectpicker.post.dto.response;

import com.example.projectpicker.post.entity.HashTagEntity;
import com.example.projectpicker.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 게시글 내부 응답 DTO ( 클라이언트 <---- DB )
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostResponseDTO {

    private String PostId; // 게시판 식별 코드(아이디)

    private String userName; // 작성자 이름

    private String userEmail; // 작성자 이메일
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private List<String> hashTags; // 게시글 해쉬태그
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime createDate; // 게시글 생성 시간


    // PostEntity 로 부터 엔티티를 받아서 DTO 로 만들어주는 생성자

    public PostResponseDTO(PostEntity postEntity) {
        this.PostId = postEntity.getPostId(); // 게시글 식별 코드
        this.userName = postEntity.getUser().getUserName(); // 게시글 작성자 이름
        this.userEmail = postEntity.getUser().getEmail(); // 게시글 작성자 이메일
        this.title = postEntity.getPostTitle(); // 게시글 제목
        this.content = postEntity.getPostContent(); // 게시글 내용
        this.createDate = postEntity.getCreateDate(); // 게시글 생성 시간


        this.hashTags = postEntity.getHashTags() // 게시글 해쉬태그
                .stream()
                .map(HashTagEntity::getTagName)
                .collect(Collectors.toList());


    }
}
