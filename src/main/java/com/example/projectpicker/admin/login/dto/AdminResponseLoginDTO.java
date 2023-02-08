package com.example.projectpicker.admin.login.dto;

import com.example.projectpicker.admin.login.entity.AdminPostEntity;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AdminResponseLoginDTO {
    private String adminEmail;
    private String adminPassword;
    private String token; // 인증 토큰
    private String message; // 응답 메시지
    public AdminResponseLoginDTO(AdminPostEntity adminPostEntity, String token){
        this.adminEmail = adminPostEntity.getAdminEmail();
        this.adminPassword = adminPostEntity.getAdminPassword();
        this.token = token;
        this.message = adminPostEntity.getMessage();
    }
}
