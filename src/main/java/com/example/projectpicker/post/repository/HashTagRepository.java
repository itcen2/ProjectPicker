package com.example.projectpicker.post.repository;

import com.example.projectpicker.post.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity,Long> {
}
