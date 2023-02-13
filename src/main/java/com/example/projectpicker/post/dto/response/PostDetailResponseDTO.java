package com.example.projectpicker.post.dto.response;

import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.post.entity.HashTagEntity;
import com.example.projectpicker.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 파일 설명: 수정 했을때 게시글 내부 정보 수정?
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class PostDetailResponseDTO extends PostResponseDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime modifyDate; // 게시글 수정 시간

    private String content;

    private List<String> hashTags; // 게시글 해쉬태그

    public PostDetailResponseDTO(PostEntity postEntity){
        super(postEntity);
        this.modifyDate = postEntity.getModifyDate();
        this.content = postEntity.getPostContent();

        this.hashTags = postEntity.getHashTags() // 게시글 해쉬태그
                .stream()
                .map(HashTagEntity::getTagName)
                .collect(Collectors.toList());
    }
}