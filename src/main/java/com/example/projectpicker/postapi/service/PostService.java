package com.example.projectpicker.postapi.service;


import com.example.projectpicker.postapi.dto.request.PageRequestDTO;
import com.example.projectpicker.postapi.dto.response.PostListResponseDTO;
import com.example.projectpicker.postapi.entity.PostEntity;
import com.example.projectpicker.postapi.repository.HashTagRepository;
import com.example.projectpicker.postapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable; // 직접 import 문 넣었음.

import java.util.List;

// 게시판 서비스 part (레포지토리,DTO 를 주로 가져와서 만든다)
@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;


    // 게시글 목록 조회 중간처리
    public PostListResponseDTO getList(PageRequestDTO pageRequestDTO){

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC,
                "createDate"
        );

        final Page<PostEntity> pageData = postRepository.findAll(pageable);
        List<PostEntity> list = pageData.getContent();

        if(list.isEmpty()){
            throw new RuntimeException("조회 결과가 없습니다.");
        }

        // 엔티티 리스트를 DTO리스트로 변환해서 클라이언트에 응답.



    }
}
