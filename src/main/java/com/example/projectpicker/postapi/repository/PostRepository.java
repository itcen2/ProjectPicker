package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity, String> {

    List<PostEntity> findByAllowTrue();

    Page<PostEntity> findByAllowTrue(Pageable pageable);

    Page<PostEntity> findByPostTitleContaining(String keyword, Pageable pageable);

    Page<PostEntity> findByAllowTrueAndPostTitleContaining(String keyword, Pageable pageable);

    @Query(value = "SELECT tbl_post.* FROM tbl_hashtag JOIN tbl_post ON tbl_post.post_id = tbl_hashtag.post_id WHERE tbl_hashtag.tag_name IN (?1, ?2) GROUP BY post_id HAVING COUNT(tag_id) >= 2;")
    List<PostEntity> HashTagsSearch(String keyword1, String keyword2, Pageable pageable);


}
