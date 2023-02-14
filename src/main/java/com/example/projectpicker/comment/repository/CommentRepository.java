package com.example.projectpicker.comment.repository;

import com.example.projectpicker.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query(value = "select * from tbl_comment where post_id=:postId", nativeQuery = true)
    List<CommentEntity> getComments(@Param("postId") String postId);
    @Query(value = "DELETE FROM tbl_comment WHERE post_id = :postId", nativeQuery = true)
    void deleteByPostId(@Param("postId") String postId);
}
