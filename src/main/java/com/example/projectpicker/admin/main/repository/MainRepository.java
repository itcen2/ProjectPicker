package com.example.projectpicker.admin.main.repository;

import com.example.projectpicker.admin.main.dto.MainPostDTO;
import com.example.projectpicker.admin.main.entity.MainPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainRepository extends JpaRepository<MainPostEntity, String> {

    List<MainPostEntity> findByAllow(Boolean allow);

}
