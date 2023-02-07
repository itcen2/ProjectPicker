package com.example.projectpicker.postapi.dto.response;

import com.example.projectpicker.postapi.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

// 파일 설명: 수정 했을때 게시글 내부 정보 수정?
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class PostDetailResponseDTO extends PostResponseDTO{

    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime modifyDate; // 게시글 수정 시간

    public PostDetailResponseDTO(PostEntity postEntity){
        super(postEntity);
        this.modifyDate = postEntity.getModifyDate();
    }
}
