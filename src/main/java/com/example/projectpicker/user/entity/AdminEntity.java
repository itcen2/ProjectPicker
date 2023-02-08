package com.example.projectpicker.user.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tbl_admin")
public class AdminEntity {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy ="uuid")
    private String id; // 관리자 계정 식별 코드

    @Column(name = "admin_email",unique = true, nullable = false)
    private String email; // 관리자 이메일

    @Column(name = "admin_password",nullable = false)
    private String password; // 관리자 비밀번호
}
