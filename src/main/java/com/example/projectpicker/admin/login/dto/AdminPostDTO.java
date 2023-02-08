package com.example.projectpicker.admin.login.dto;

import com.example.projectpicker.admin.login.entity.AdminPostEntity;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AdminPostDTO {
    private String adminEmail;
    private String adminPassword;

    public AdminPostDTO(AdminPostEntity adminPostEntity){
        this.adminEmail = adminPostEntity.getAdminEmail();
        this.adminPassword = adminPostEntity.getAdminPassword();
    }
}
