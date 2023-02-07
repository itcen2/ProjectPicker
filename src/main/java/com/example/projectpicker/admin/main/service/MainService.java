package com.example.projectpicker.admin.main.service;

import com.example.projectpicker.admin.main.dto.MainPostDTO;
import com.example.projectpicker.admin.main.entity.MainPostEntity;
import com.example.projectpicker.admin.main.repository.MainRepository;
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
    private final MainRepository mainRepository;

    // 허용된 게시물과 안된 게시물
    public List<MainPostDTO> findDenyList(){
        List<MainPostEntity> all = mainRepository.findByAllow(false);

        return all.stream()
                .map(MainPostDTO::new)
                .collect(Collectors.toList());

    }
    public List<MainPostDTO> findAllowList(){
        List<MainPostEntity> all = mainRepository.findByAllow(true);

        return all.stream()
                .map(MainPostDTO::new)
                .collect(Collectors.toList());

    }
    public List<MainPostDTO> findAllList(){
        List<MainPostEntity> all = mainRepository.findAll();

        return all.stream()
                .map(MainPostDTO::new)
                .collect(Collectors.toList());

    }
    public List<MainPostDTO> modifyItem(final String postId, final MainPostDTO mainPostDTO){
        Optional<MainPostEntity> post = mainRepository.findById(postId);
        post.ifPresent(entity -> {
            entity.setAllow(!entity.getAllow());
            mainRepository.save(entity);
        });
        return findAllList();
    }

    public List<MainPostDTO> deleteItem(final String postId){
        try {
            mainRepository.deleteById(postId);
            return findAllList();
        } catch (Exception e) {
            throw new RuntimeException("게시물을 삭제할 수 없습니다.");
        }
    }

    public MainPostDTO postDetail(final String postId){
        MainPostEntity detail = mainRepository.findById(postId).orElseThrow();
        return new MainPostDTO(detail);
    }
}
