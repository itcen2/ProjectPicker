package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    HashTagRepository hashTagRepository;

    @BeforeEach
    void insertTest() {
        PostEntity post1 = PostEntity.builder()
                .postTitle("트루")
                .postContent("이건 트루")
                .allow(true)
                .build();
        PostEntity post2 = PostEntity.builder()
                .postTitle("거짓")
                .postContent("이건 거짓")
                .allow(false)
                .build();
        PostEntity post3 = PostEntity.builder()
                .postTitle("트루2")
                .postContent("이건 트루2")
                .allow(true)
                .build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }

    @Test
    @DeleteMapping("허가된 포스트를 조회하면 리스트의 사이즈가 2이어야 한다.")
    void findAllTest() {
        //given
        //when
        List<PostEntity> list = postRepository.findByAllowTrue();
        //then
        assertEquals(2, list.size());
    }
}