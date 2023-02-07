package com.example.projectpicker.postapi.dto.response;

import com.example.projectpicker.postapi.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostDetailResponseDTO extends PostResponseDTO{

    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime modDate;

    public PostDetailResponseDTO(PostEntity entity) {
        super(entity);
        this.modDate = entity.getModifyDate();
    }
}
