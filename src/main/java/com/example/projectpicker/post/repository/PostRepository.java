package com.example.projectpicker.post.repository;

import com.example.projectpicker.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository


/**
 * 검색 필터 레포지토리 part
 */

//<어떤 엔티티를 넣을것이냐, 엔티티의 id 타입>
public interface PostRepository extends JpaRepository<PostEntity, String> {


    //findByTitleContaining: title 컬럼을 기준으로 해당 키워드가 포함된것을 찾겠다.
    Page<PostEntity> findByPostTitleContaining(String keyword, Pageable pageable);

}

