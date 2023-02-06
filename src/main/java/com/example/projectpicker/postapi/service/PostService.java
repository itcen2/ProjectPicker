package com.example.projectpicker.postapi.service;

import com.example.projectpicker.postapi.dto.request.PageRequestDTO;
import com.example.projectpicker.postapi.dto.response.PageResponseDTO;
import com.example.projectpicker.postapi.dto.response.PostListResponseDTO;
import com.example.projectpicker.postapi.dto.response.PostResponseDTO;
import com.example.projectpicker.postapi.entity.HashTagEntity;
import com.example.projectpicker.postapi.entity.PostEntity;
import com.example.projectpicker.postapi.repository.HashTagRepository;
import com.example.projectpicker.postapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    // 게시글 목록 조회
    @Transactional
    public PostListResponseDTO getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC,
                "createDate"
        );

        final Page<PostEntity> pageData = postRepository.findAll(pageable);
        List<PostEntity> list = pageData.getContent();

        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없습니다.");
        }

        // 엔터티 리스트를 DTO리스트로 변환해서 클라이언트에 응답
        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(toList());

        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<PostEntity>(pageData))
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }
}
