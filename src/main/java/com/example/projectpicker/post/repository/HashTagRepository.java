package com.example.projectpicker.post.repository;

import com.example.projectpicker.post.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagEntity,String> {
    @Query(value = "DELETE FROM tbl_hashtag WHERE post_id = :postId", nativeQuery = true)
    void deletePostId(@Param("postId") String postId);

}