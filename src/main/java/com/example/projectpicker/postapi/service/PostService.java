package com.example.projectpicker.postapi.service;

import com.example.projectpicker.postapi.dto.request.PageRequestDTO;
import com.example.projectpicker.postapi.dto.request.PostCreateRequestDTO;
import com.example.projectpicker.postapi.dto.request.PostModifyRequestDTO;
import com.example.projectpicker.postapi.dto.response.PageResponseDTO;
import com.example.projectpicker.postapi.dto.response.PostDetailResponseDTO;
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
import java.util.ArrayList;
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

    // 개별 조회 중간처리
    public PostDetailResponseDTO getDetail(String postId) {
        PostEntity post = postRepository
                .findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("게시물이 존재하지 않음!!"));

        // 엔터티를 DTO로 변환
        return new PostDetailResponseDTO(post);
    }

    // 게시글 등록 중간처리
    @Transactional
    public PostDetailResponseDTO insert(final PostCreateRequestDTO createDTO)
        throws RuntimeException {

        // dto를 entity변환 작업
        final PostEntity entity = createDTO.toEntity();

        PostEntity savedPost = postRepository.save(entity);

        // hashtag를 db에 저장
        List<String> hashTags = createDTO.getHashTags();

        // 해시태그 문자열 리스트에서 문자열들을 하나하나 추출한 뒤
        // 해시태그 엔터티로 만들고 그 엔터티를 데이터베이스에 저장한다.
        List<HashTagEntity> hashTagEntities = new ArrayList<>();
        for (String ht : hashTags) {
            HashTagEntity tagEntity = HashTagEntity.builder()
                    .post(savedPost)
                    .tagName(ht)
                    .build();

            HashTagEntity savedTag = hashTagRepository.save(tagEntity);
            hashTagEntities.add(savedTag);
        }
        savedPost.setHashTags(hashTagEntities);


        // 저장된 객체를 DTO로 변환해서 반환
        return new PostDetailResponseDTO(savedPost);
    }

    // 수정 중간 처리
    public PostDetailResponseDTO update(final String postId, final PostModifyRequestDTO modifyDTO)
            throws RuntimeException {
        // 수정 전 데이터 조회하기
        PostEntity entity = postRepository.
                findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("수정 전 데이터가 존재하지 않습니다."));
        // 수정 진행
        String modTitle = modifyDTO.getTitle();
        String modContent = modifyDTO.getContent();

        if (modTitle != null) entity.setPostTitle(modTitle);
        if (modContent != null) entity.setPostContent(modContent);

        PostEntity modifyPost = postRepository.save(entity);
        return new PostDetailResponseDTO(modifyPost);
    }
}
