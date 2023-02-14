package com.example.projectpicker.user.dto;

import com.example.projectpicker.user.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email") // email 만 비교해라. 굳이 비번,이름까지 비교할 필요x
@Builder

// 회원가입시 클라이언트가 보낸 데이터를 담는 객체 ( 클라이언트 ----> DB )
public class UserInfoUpdateRequestDTO {

    @NotBlank
    @Size(min = 8, max = 20) // 8~20자 여야 한다.
    private String password; // 회원 현재 비밀번호


    @NotBlank
    @Size(min = 8, max = 20) // 8~20자 여야 한다.
    private String updatePassword; // 회원 수정 비밀번호

}
