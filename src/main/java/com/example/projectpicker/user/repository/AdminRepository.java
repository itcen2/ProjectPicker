package com.example.projectpicker.user.repository;

import com.example.projectpicker.user.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<AdminEntity,String>{

    // 이메일로 회원 조회
    AdminEntity findByEmail(String email);


    // 이메일 중복 검사
    boolean existsByEmail(String email);
}

/*
고민
1. 관리자는 이메일을 통한 관리자회원을 조회할것인지..
2. 관리자도 이메일 중복검사 필요하지 않은지..
*/
