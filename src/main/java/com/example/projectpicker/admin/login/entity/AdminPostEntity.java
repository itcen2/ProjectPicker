package com.example.projectpicker.admin.login.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tbl_admin")
@EqualsAndHashCode(of = "adminId")
public class AdminPostEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "admin_id")
    private String adminId;

    @Column(nullable = false, name = "admin_email")
    private String adminEmail;

    @Column(nullable = false, name = "admin_password")
    private String adminPassword;

    private String message;
}
