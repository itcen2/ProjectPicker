package com.example.projectpicker.post.repository;

import com.example.projectpicker.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository


/**
 * 검색 필터 레포지토리 part
 */

//<어떤 엔티티를 넣을것이냐, 엔티티의 id 타입>
public interface PostRepository extends JpaRepository<PostEntity, String> {


//    List<PostEntity> findByAllowTrue(Pageable pageable);
    Page<PostEntity> findByAllowTrue(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM tbl_post  WHERE tbl_Post.post_id IN(:ids)")
    Page<PostEntity> findPostId(@Param("ids") List<String> ids, Pageable pageable);

    // 모든 포스트 제목 검색 (허용여부 상관없이)
    Page<PostEntity> findByPostTitleContaining(String keyword, Pageable pageable);//findByTitleContaining: title 컬럼을 기준으로 해당 키워드가 포함된것을 찾겠다.

    // 허용된 포스트 제목 검색
    Page<PostEntity> findByAllowTrueAndPostTitleContaining(String keyword, Pageable pageable);

    // 해시태그로 검색
    @Query(value = "SELECT tbl_post.post_id FROM tbl_hashtag JOIN tbl_post ON tbl_post.post_id = tbl_hashtag.post_id " +
            "WHERE tbl_hashtag.tag_name IN (:keyword1, :keyword2) " +
            "GROUP BY post_id HAVING COUNT(tag_id) >= 2", nativeQuery = true)
    List<String> HashTagsSearch(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2);

}



