package com.example.projectpicker.postapi.repository;

import com.example.projectpicker.postapi.entity.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
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

        ArrayList list = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList list3 = new ArrayList();

        list.add("검색");
        list.add("test");
        list2.add("ㄹㄷㅈㅁㄹ");
        list2.add("test");
        list3.add("검색");
        list3.add("test");


        PostEntity post1 = PostEntity.builder()
                .postTitle("트루")
                .postContent("이건 트루")
                .hashTags(list)
                .allow(true)
                .build();
        PostEntity post2 = PostEntity.builder()
                .postTitle("거짓")
                .postContent("이건 거짓")
                .hashTags(list2)
                .allow(false)
                .build();
        PostEntity post3 = PostEntity.builder()
                .postTitle("트루2")
                .postContent("이건 트루2")
                .hashTags(list3)
                .allow(true)
                .build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }

    @Test
    @DisplayName("허가된 포스트를 조회하면 리스트의 사이즈가 2이어야 한다.")
    void findAllTest() {
        //given
        //when
        List<PostEntity> list = postRepository.findByAllowTrue();
        //then
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("검색과 test로 검색을 하면 리스트의 사이즈가 2이어야 한다.")
    void findHashTagsTest() {
        //given
        //when
        List<PostEntity> list = postRepository.findByHashTags("검색", "test");
        //then
        assertEquals(2, list.size());
    }

}