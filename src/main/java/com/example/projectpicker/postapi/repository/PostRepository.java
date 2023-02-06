package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface PostRepository extends JpaRepository<PostEntity, String> {

}

