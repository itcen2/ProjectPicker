package com.example.projectpicker.postapi.dto.response;

import com.example.projectpicker.postapi.entity.HashTagEntity;
import com.example.projectpicker.postapi.entity.PostEntity;
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
    private List<String> hashTags; // 게시글 해시태그

    @JsonFormat(pattern = "yyyy년 MM월 dd일 hh시 mm분 ss초 ")
    private LocalDateTime createDate; // 게시글 생성 시간

    @JsonFormat(pattern = "yyyy년 MM월 dd일 hh시 mm분 ss초 ")
    private LocalDateTime modifyDate; // 게시글 수정 시간


    // PostEntity로 부터 엔티티를 받아서 DTO로 만들어주는 생성자

    public PostResponseDTO(PostEntity postEntity) {
        this.PostId = postEntity.getPostId();
        this.userName = postEntity.getUser().getUserName();
        this.title = postEntity.getPostTitle();
        this.content = postEntity.getPostContent();
        this.createDate = postEntity.getCreatedate();
        this.modifyDate = postEntity.getModifyDate();

        this.hashTags = postEntity.getHashTags()
                .stream()
                .map(HashTagEntity::getTagName)
                .collect(Collectors.toList());
    }
}
