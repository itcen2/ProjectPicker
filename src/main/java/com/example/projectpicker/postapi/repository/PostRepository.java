package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // 제목으로 검색 후 페이징처리
    Page<PostEntity> findByTitleContaining(String title, Pageable pageable);
}

