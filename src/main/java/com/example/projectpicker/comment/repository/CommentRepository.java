package com.example.projectpicker.comment.repository;

import com.example.projectpicker.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {

    @Query(value = "select * from tbl_comment where post_id=:postId", nativeQuery = true)
    List<CommentEntity> getComments(@Param("postId") String postId);
}
