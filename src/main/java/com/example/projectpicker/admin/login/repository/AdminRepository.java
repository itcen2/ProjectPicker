package com.example.projectpicker.admin.login.repository;

import com.example.projectpicker.admin.login.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    AdminEntity findByAdminEmail(String adminEmail);
}
