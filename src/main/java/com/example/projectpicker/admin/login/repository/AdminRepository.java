package com.example.projectpicker.admin.login.repository;

import com.example.projectpicker.admin.login.entity.AdminPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminPostEntity, String> {

    AdminPostEntity findByAdminEmail(String adminEmail);
}
