package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity, String> {

    List<PostEntity> findByAllowTrue();

    Page<PostEntity> findByAllowTrue(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM tbl_post  WHERE tbl_Post.post_id IN(:ids)")
    Page<PostEntity> findPostId(@Param("ids") List<String> ids, Pageable pageable);

    Page<PostEntity> findByPostTitleContaining(String keyword, Pageable pageable);

    Page<PostEntity> findByAllowTrueAndPostTitleContaining(String keyword, Pageable pageable);

    @Query(value = "SELECT tbl_post.post_id FROM tbl_hashtag JOIN tbl_post ON tbl_post.post_id = tbl_hashtag.post_id WHERE tbl_hashtag.tag_name IN (:keyword1, :keyword2) GROUP BY post_id HAVING COUNT(tag_id) >= 2", nativeQuery = true)
    List<String> HashTagsSearch(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2);


}
