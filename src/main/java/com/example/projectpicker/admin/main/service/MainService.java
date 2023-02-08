package com.example.projectpicker.admin.main.service;

import com.example.projectpicker.admin.main.dto.MainPostDTO;
import com.example.projectpicker.postapi.entity.PostEntity;
import com.example.projectpicker.postapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service //스프링 빈 등록
@RequiredArgsConstructor
@Slf4j
public class MainService {

    @Autowired
    private final PostRepository postRepository;

    // 허용된 게시물과 안된 게시물
   /* public List<MainPostDTO> findDenyList(){
        List<PostEntity> all = postRepository.findByAllowTrue();

        return all.stream()
                .map(MainPostDTO::new)
                .collect(Collectors.toList());

    }*/
    public List<MainPostDTO> findAllowList(){
        List<PostEntity> all = postRepository.findByAllowTrue();

        return all.stream()
                .map(MainPostDTO::new)
                .collect(Collectors.toList());

    }
    public List<MainPostDTO> findAllList(){
        List<PostEntity> all = postRepository.findAll();

        return all.stream()
                .map(MainPostDTO::new)
                .collect(Collectors.toList());

    }
    public List<MainPostDTO> modifyItem(final String postId, final MainPostDTO mainPostDTO){
        Optional<PostEntity> post = postRepository.findById(postId);
        post.ifPresent(entity -> {
            entity.setAllow(!entity.isAllow());
            postRepository.save(entity);
        });
        return findAllList();
    }

    public List<MainPostDTO> deleteItem(final String postId){
        try {
            postRepository.deleteById(postId);
            return findAllList();
        } catch (Exception e) {
            throw new RuntimeException("게시물을 삭제할 수 없습니다.");
        }
    }

    public MainPostDTO postDetail(final String postId){
        Optional<PostEntity> post = postRepository.findById(postId);

        return new MainPostDTO(post.get());
    }
}
