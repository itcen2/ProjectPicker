package com.example.projectpicker.admin.login.dto;

import com.example.projectpicker.admin.login.entity.AdminPostEntity;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AdminSignUpDTO {
    private String adminEmail;
    private String adminPassword;

    public AdminPostEntity toEntity(){
        return AdminPostEntity.builder()
                .adminEmail(this.adminEmail)
                .adminPassword(this.adminPassword)
                .build();
    }
}
