package com.example.projectpicker.user.dto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 회원가입시 클라이언트가 보낸 데이터를 담는 객체 ( 클라이언트 ----> DB )
public class UserInfoUpdateRequestDTO {

    @NotBlank
    @Size(min = 8, max = 20) // 8~20자 여야 한다.
    private String rawPassword; // 회원 비밀번호
    // @NotBlank
    @Size(min = 8, max = 20) // 8~20자 여야 한다.
    private String password; // 회원 비밀번호

}