package com.example.projectpicker.admin.login.dto;

import com.example.projectpicker.admin.login.entity.AdminEntity;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AdminPostDTO {
    private String adminEmail; // 관리자 이메일
    private String adminPassword; // 관리자 비밀번호

    // 로그인 정보를 받아서 클라이언트에게
    // 관리자 로그인시 받은 이메일 , 패스워드 데이터 (DTO <- Entity)

}
