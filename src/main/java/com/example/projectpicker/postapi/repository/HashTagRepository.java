package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.HashTagEntity;
import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTagEntity, String> {
}
