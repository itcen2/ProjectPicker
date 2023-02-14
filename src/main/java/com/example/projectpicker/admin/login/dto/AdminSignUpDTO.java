package com.example.projectpicker.admin.login.dto;

import com.example.projectpicker.admin.login.entity.AdminEntity;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AdminSignUpDTO {
    private String adminEmail; // 관리자 이메일
    private String adminPassword; // 관리자 비밀번호


    // 클라이언트로 받은 데이터를 엔티티로 ~ (관리자 회원가입) (DTO -> Entity)
    public AdminEntity toEntity(){
        return AdminEntity.builder()
                .adminEmail(this.adminEmail)
                .adminPassword(this.adminPassword)
                .build();
    }
}
