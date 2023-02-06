package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashtagEntity,Long> {
}
