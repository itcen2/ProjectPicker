package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity, String> {

    @Query
    List<PostEntity> findByAllowTrue();

    @Query
    Page<PostEntity> findByAllowTrue(Pageable pageable);

    @Query
    List<PostEntity> findByPostTitleContaining(String keyword);

    @Query
    Page<PostEntity> findByPostTitleContaining(String keyword, Pageable pageable);

}
