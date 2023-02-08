package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.HashTagEntity;
import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagEntity, String> {

    @Query(value = "DELETE FROM tbl_hashtag WHERE post_id = :postId", nativeQuery = true)
    void deletePostId(@Param("postId") String postId);

}
