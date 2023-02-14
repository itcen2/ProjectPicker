package com.example.projectpicker.user.dto;


import com.example.projectpicker.post.dto.response.PostListDataResponseDTO;
import com.example.projectpicker.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

// 로그인시 클라이언트에게 응답해주는 객체 ( 클라이언트 <---- DB)
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MypageRequestDTO {
    @NotBlank
    private String userId;

}
