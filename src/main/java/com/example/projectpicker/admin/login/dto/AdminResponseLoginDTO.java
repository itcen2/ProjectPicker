package com.example.projectpicker.admin.login.dto;

import com.example.projectpicker.admin.login.entity.AdminEntity;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AdminResponseLoginDTO {
    private String adminEmail; //관리자 이메일
    private String adminPassword; // 관리자 비밀번호
    private String token; // 인증 토큰
    private String message; // 응답 메시지

    // 로그인 후 사용자에게 token 및 관리자 정보 데이터 전달..(DTO < - Entity)
    public AdminResponseLoginDTO(AdminEntity adminPostEntity, String token){
        this.adminEmail = adminPostEntity.getAdminEmail();
        this.adminPassword = adminPostEntity.getAdminPassword();
        this.token = token;
        this.message = adminPostEntity.getMessage();
    }
}
