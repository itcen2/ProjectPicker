package com.example.projectpicker.comment.repository;

import com.example.projectpicker.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    CommentEntity findByPostId(String postId);
}
