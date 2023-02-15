package com.example.projectpicker.post.dto.response;

import com.example.projectpicker.comment.dto.CommentResponseDTO;
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
public class PostListDataResponseDTO { //게시판 리스트 데이터 가져오는녀석

    private String PostId; // 게시판 식별 코드(아이디)

    private String userName; // 작성자 이름

    private String userEmail; // 작성자 이메일
    private String title; // 게시글 제목

    private boolean status;  // 게시글 모집, 마감

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime createDate; // 게시글 생성 시간

    private List<String> hashTags; // 게시글 해쉬태그

    public PostListDataResponseDTO(PostEntity postEntity) {
        this.PostId = postEntity.getPostId(); // 게시글 식별 코드
        this.userName = postEntity.getUserName(); // 게시글 작성자 이름 - 강사님이 추가하신 코드
        this.userEmail = postEntity.getUserEmail(); // 게시글 작성자 이메일 -  강사님이 추가하신 코드
        this.title = postEntity.getPostTitle(); // 게시글 제목
        this.createDate = postEntity.getCreateDate(); // 게시글 생성 시간
        this.status= postEntity.isStatus();


        this.hashTags = postEntity.getHashTags() // 게시글 해쉬태그
                .stream()
                .map(HashTagEntity::getTagName)
                .collect(Collectors.toList());


    }
}