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
public class UserSignUpDTO {

    @NotBlank // null 과 ""(empty), " "(blank) 모두 허용하지 않음.
    @Email
    private String email; // 회원 이메일

    @NotBlank
    @Size(min = 8, max = 20) // 8~20자 여야 한다.
    private String password; // 회원 비밀번호

    @NotBlank
    @Size(min = 2, max = 5) // 2~5자 여야 한다.
    private String userName; // 회원 이름

    // 엔티티로 변경하는 메서드
    public UserEntity toEntity(){
        return UserEntity.builder()
                .userEmail(this.email) //클라이언트로 부터 받은 email 정보를 UserEntity값 변경
                .userPassword(this.password)
                .userName(this.userName)
                .build();
    }
}