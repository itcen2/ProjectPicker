package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity,Long> {
}
