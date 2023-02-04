package com.example.projectpicker.userapi.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // id 만 비교해라. 굳이 비번,이름까지 비교할 필요x
@Builder
@Entity
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // id가 중복되지 않도록
    private String id; // 식별 코드

    @Column(name = "user_email",unique = true, nullable = false) // unique=true: 중복x / nullable=false : 필수 값
    private String email; // 이메일

    @Column(name = "user_password",nullable = false)
    private String password; // 비밀번호

    @Column(name ="user_name",nullable = false)
    private String userName; // 회원 이름

    @CreationTimestamp
    private LocalDateTime joinDate; // 가입 날짜

}
