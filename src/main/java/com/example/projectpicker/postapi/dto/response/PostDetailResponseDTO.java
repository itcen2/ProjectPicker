package com.example.projectpicker.postapi.dto.response;

import com.example.projectpicker.postapi.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

// 파일 설명: 수정 했을때 게시글 내부 정보 수정?
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class PostDetailResponseDTO extends PostResponseDTO{
    private LocalDateTime modDate;

    public PostDetailResponseDTO(PostEntity entity){
        super(entity);
        this.modDate = entity.getModifyDate();
    }
}
