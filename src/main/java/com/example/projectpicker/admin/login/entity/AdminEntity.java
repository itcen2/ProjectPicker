package com.example.projectpicker.admin.login.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tbl_admin")
@EqualsAndHashCode(of = "adminId")
public class AdminEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "admin_id")
    private String adminId; // 관리자 식별 아이디

    @Column(nullable = false, name = "admin_email")
    private String adminEmail; // 관리자 이메일

    @Column(nullable = false, name = "admin_password")
    private String adminPassword; //관리자 비밀번호

    private String message; // 관리자 비밀번호 성공(일치) 여부 메시지
}
