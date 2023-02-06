package com.example.projectpicker.postapi.controller;

import com.example.projectpicker.postapi.dto.request.PageRequestDTO;
import com.example.projectpicker.postapi.dto.response.PageResponseDTO;
import com.example.projectpicker.postapi.dto.response.PostListResponseDTO;
import com.example.projectpicker.postapi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/post")

public class PostController {
    private final PostService postService;

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<?> list(PageRequestDTO pageRequestDTO){
        log.info("request page info - {}", pageRequestDTO);

        try {
            PostListResponseDTO listResponseDTO = postService.getList(pageRequestDTO);
            return ResponseEntity
                    .ok()
                    .body(listResponseDTO);
        } catch (Exception e){
            return ResponseEntity
                    .notFound()
                    .build();

        }
    }

    // 게시글 개별 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> detail(@PathVariable String postId){
        log.info("/projectpicker/{} GET request", postId);
        try{

        }
    }

    // 프로젝트 모집 게시글 요청

    // 게시글 수청

    // 게시글 삭제
}
