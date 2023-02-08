package com.example.projectpicker.postapi.dto.request;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostModifyRequestDTO {

    private String title;

    private String content;
}
