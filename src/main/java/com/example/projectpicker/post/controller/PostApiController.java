package com.example.projectpicker.post.controller;


import com.example.projectpicker.post.dto.request.PageRequestDTO;
import com.example.projectpicker.post.dto.request.PostCreateRequestDTO;
import com.example.projectpicker.post.dto.request.PostModifyRequestDTO;
import com.example.projectpicker.post.dto.response.PostCommentDetailResponseDTO;
import com.example.projectpicker.post.dto.response.PostDetailResponseDTO;
import com.example.projectpicker.post.dto.response.PostListResponseDTO;
import com.example.projectpicker.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/projectpicker")    // 받을 주소
@CrossOrigin
public class PostApiController {

    private final PostService postService;

    private final String page = "/page/{i}";


    // 게시글 목록 조회
    @GetMapping("/page/{i}")
    public ResponseEntity<?> list(PageRequestDTO pageRequestDTO,@PathVariable("i") int i) {
        log.info("request page info - {}", pageRequestDTO);

        pageRequestDTO.setPage(i);
        try {
            PostListResponseDTO listResponseDTO = postService.getList(pageRequestDTO);
            return ResponseEntity
                    .ok()
                    .body(listResponseDTO)
                    ;
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }

    }

    /**
     *  게시판 검색
     *  postman ( http://localhost:8080/projectpicker/search/ 게시판 제목 ) --GET
     */
    @GetMapping("/search/{string}/page/{i}")
    public ResponseEntity<?> searchList(PageRequestDTO pageRequestDTO,@PathVariable String string, @PathVariable("i") int i) {
        log.info("request page info - {}", pageRequestDTO);
        pageRequestDTO.setPage(i);

        try {
            PostListResponseDTO listResponseDTO = postService.searchList(pageRequestDTO,string);
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


    /**
     * 게시글 해시태그 검색 (해시태그는 1~2개까지만)
     */


    @GetMapping("/hashsearch/{keyword1}/{keyword2}/page/{i}")
    public ResponseEntity<?> hashTagSearch2(PageRequestDTO pageRequestDTO,
                                            @PathVariable String keyword1,
                                            @PathVariable String keyword2
            , @PathVariable("i") int i) {
        log.info("request page info - {}", pageRequestDTO);
        pageRequestDTO.setPage(i);

        try {
            PostListResponseDTO listResponseDTO = postService.searchHashTagList2(keyword1, keyword2, pageRequestDTO);
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

    @GetMapping("/hashsearch/{keyword3}/page/{i}")
    public ResponseEntity<?> hashTagSearch1(PageRequestDTO pageRequestDTO,
                                            @PathVariable String keyword3
            , @PathVariable("i") int i) {
        log.info("request page info - {}", pageRequestDTO);
        pageRequestDTO.setPage(i);

        try {
            PostListResponseDTO listResponseDTO = postService.searchHashTagList1(keyword3, pageRequestDTO);
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
            PostCommentDetailResponseDTO dto = postService.getDetail(postId);
            log.info(dto.getPostId());
            return ResponseEntity
                    .ok()
                    .body(dto)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError().body(e.getMessage());
        }

    }

    // 게시글 등록
    // Postman (localhost:8080/projectpicker) --POST
    @PostMapping
    public ResponseEntity<?> createPost(
            @Validated @RequestBody PostCreateRequestDTO requestDTO
            , BindingResult result  // 검증 에러 정보를 갖고 있는 객체
            , @AuthenticationPrincipal String userId  // 강사님이 추가하신 코드
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
            PostDetailResponseDTO responseDTO = postService.insert(requestDTO, userId); // 강사님이 userID 추가하셨음.
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
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

    // 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> remove(@PathVariable String postId) {
        try {
            postService.delete(postId);
            return ResponseEntity
                    .ok()
                    .body("Delete Success");
        } catch (RuntimeException e) {
            log.error("delete fail : caused by - {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }
}