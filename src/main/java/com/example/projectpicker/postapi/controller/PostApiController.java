package com.example.projectpicker.postapi.controller;


import com.example.projectpicker.postapi.dto.request.PageRequestDTO;
import com.example.projectpicker.postapi.dto.request.PostCreateRequestDTO;
import com.example.projectpicker.postapi.dto.request.PostModifyRequestDTO;
import com.example.projectpicker.postapi.dto.response.PostDetailResponseDTO;
import com.example.projectpicker.postapi.dto.response.PostListResponseDTO;
import com.example.projectpicker.postapi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/projectpicker")    // 받을 주소
public class PostApiController {

    private final PostService postService;

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<?> list(PageRequestDTO pageRequestDTO) {
        log.info("request page info - {}", pageRequestDTO);

        try {
            PostListResponseDTO listResponseDTO = postService.getList(pageRequestDTO);
            return ResponseEntity
                    .ok()
                    .body(listResponseDTO)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }

    }

    // 게시글 개별 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> detail(@PathVariable String postId) {
        log.info("/projectpicker/{} GET request", postId);

        try {
            PostDetailResponseDTO dto = postService.getDetail(postId);

            return ResponseEntity
                    .ok()
                    .body(dto)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

    }

    // 프로젝트 모집 게시글 요청
    @PostMapping
    public ResponseEntity<?> createPost(
            @Validated @RequestBody PostCreateRequestDTO requestDTO
            , BindingResult result  // 검증 에러 정보를 갖고 있는 객체
    ) {
        if (requestDTO == null) {
            return ResponseEntity
                    .badRequest()
                    .body("게시물 정보가 없습니다.");
        }

        if (result.hasErrors()) {   // 검증에러가 발생할 시 true 리턴
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(err -> {
                log.warn("invalidated client data - {}", err.toString());
            });
            return ResponseEntity
                    .badRequest()
                    .body(fieldErrors);
        }

        try {
            PostDetailResponseDTO responseDTO = postService.insert(requestDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    // 게시물 수정
    @RequestMapping(
            value = "/{postId}"
            , method = {RequestMethod.PUT, RequestMethod.PATCH}
    )
    public ResponseEntity<?> modifyPost(
            @PathVariable("postId") String postId
            , @RequestBody PostModifyRequestDTO requestDTO
    ) {

        try {
            PostDetailResponseDTO responseDTO
                    = postService.update(postId, requestDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            log.error("update fail : caused by - {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }
}
