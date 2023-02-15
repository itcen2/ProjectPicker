package com.example.projectpicker.post.service;

import com.example.projectpicker.comment.entity.CommentEntity;
import com.example.projectpicker.comment.repository.CommentRepository;
import com.example.projectpicker.post.dto.request.PageRequestDTO;
import com.example.projectpicker.post.dto.request.PostCreateRequestDTO;
import com.example.projectpicker.post.dto.request.PostModifyRequestDTO;
import com.example.projectpicker.post.dto.response.*;
import com.example.projectpicker.post.entity.HashTagEntity;
import com.example.projectpicker.post.entity.PostEntity;
import com.example.projectpicker.post.repository.HashTagRepository;
import com.example.projectpicker.post.repository.PostRepository;
import com.example.projectpicker.user.entity.UserEntity;
import com.example.projectpicker.user.repository.UserRepository;
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
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository; // 강사님이 추가하신 코드

    /**
     * 게시글 목록 조회
     * 1. 사용자가 page(요청한 페이지번호), sizePerPage(한페이지에 보여줄 데이터 수)를 entity로 전달하게 되는데
     * 이 값을 파라미터로 받음.
     * 2.
     */
    @Transactional
    public PostListResponseDTO getList(PageRequestDTO pageRequestDTO) {

        /**
         * 👉 JPA에서 Pageable이라는 객체를 제공
         * 👉 PageRequest의 메서드
         * PageRequest.of(int page, int size, Sort sort)
         *              페이지 번호, 페이지당 데이터의 수, 정렬방향
         */
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, // 사용자 화면에선 1페이지, java 에선 0번째
                pageRequestDTO.getSizePerPage(), // 페이지 당 레코드수. 한 화면에 보여지는 라인수
                Sort.Direction.DESC,
                "createDate"
        );

        final Page<PostEntity> pageData = postRepository.findByAllowTrue(pageable);
        List<PostEntity> list = pageData.getContent(); //List<T> getContent(): 조회된 데이터 목록

        //만약 게시판 리스트 목록 데이터가 비어있을때 예외처리
        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없습니다.");
        }

        // 엔터티 리스트를 DTO 리스트로 변환해서 클라이언트에 응답
        List<PostListDataResponseDTO> responseDTOList = list.stream()
                .map(PostListDataResponseDTO::new)
                .collect(toList());

        return PostListResponseDTO.builder() // PostListResponseDTO(dto <--entity)
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<>(pageData))
                .posts(responseDTOList)
                .build();
    }


    /**
     * 특정 검색 리스트 조회 (제목 검색)
     */
    public PostListResponseDTO searchList(PageRequestDTO pageRequestDTO, String string) {
//  * 👉 PageRequest의 메서드
//         * PageRequest.of(int page, int size, Sort sort)
//         *              페이지 번호, 페이지당 데이터의 수, 정렬방향
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC,
                "createDate"
        );

        // final Page<PostEntity> pageData = postRepository.findByAllowTrueAndPostTitleContaining(string, pageable);
        final Page<PostEntity> pageData = postRepository.findByPostTitleContaining(string, pageable);
        List<PostEntity> list = pageData.getContent();

        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없습니다.");
        }

        // 엔터티 리스트를 DTO 리스트로 변환해서 클라이언트에 응답
        List<PostListDataResponseDTO> responseDTOList = list.stream()
                .map(PostListDataResponseDTO::new)
                .collect(toList());

        return PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<>(pageData))
                .posts(responseDTOList)
                .build();
    }

    /**
     * 특정 해시태그 검색 리스트 조회 중간처리 (해시태그 1개일때 , 해시태그 2개일때)
     */

    //해시태그 2개 검색
    public PostListResponseDTO searchHashTagList2(String keyword1, String keyword2, PageRequestDTO pageRequestDTO) {

        List<String> postId = postRepository.HashTagsSearch2(keyword1, keyword2);

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC,
                "create_Date"
        );

//        final Page<PostEntity> pageData = postRepository.findByAllowTrueAndPostTitleContaining(string, pageable);
        final Page<PostEntity> pageData = postRepository.findPostId(postId, pageable);
        List<PostEntity> list = pageData.getContent();

        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없습니다.");
        }

        // 엔터티 리스트를 DTO 리스트로 변환해서 클라이언트에 응답
        List<PostListDataResponseDTO> responseDTOList = list.stream()
                .map(PostListDataResponseDTO::new)
                .collect(toList());

        return PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<>(pageData))
                .posts(responseDTOList)
                .build();
    }


    //해시태그 1개 검색
    public PostListResponseDTO searchHashTagList1(String keyword3, PageRequestDTO pageRequestDTO) {

        List<String> postId = postRepository.HashTagsSearch1(keyword3);

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC,
                "create_Date"
        );

//        final Page<PostEntity> pageData = postRepository.findByAllowTrueAndPostTitleContaining(string, pageable);
        final Page<PostEntity> pageData = postRepository.findPostId(postId, pageable);
        List<PostEntity> list = pageData.getContent();

        if (list.isEmpty()) {
            throw new RuntimeException("조회 결과가 없습니다.");
        }

        // 엔터티 리스트를 DTO 리스트로 변환해서 클라이언트에 응답
        List<PostListDataResponseDTO> responseDTOList = list.stream()
                .map(PostListDataResponseDTO::new)
                .collect(toList());

        return PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<>(pageData))
                .posts(responseDTOList)
                .build();
    }



    /**
     * 개별 조회 중간처리
     */

    public PostCommentDetailResponseDTO getDetail(String postId) {
        PostEntity post = postRepository
                .findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("게시물이 존재하지 않음!!"));
        List<CommentEntity> comments = commentRepository.getComments(postId);
        // 엔터티를 DTO 로 변환
        return new PostCommentDetailResponseDTO(post, comments);
    }


    /**
     * 게시글 등록
     */
    @Transactional
    public PostDetailResponseDTO insert(final PostCreateRequestDTO createDTO
            , final String userId) // 강사님이 추가하신 코드 (final String userID)
            throws RuntimeException {

        // dto 를 entity 변환 작업
        final PostEntity entity = createDTO.toEntity();
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("사용자 정보를 찾을 수 없습니다."));// 강사님이 추가하신 코드
        log.info("user : {}", user); // 강사님이 추가하신 코드 (log 확인)
        entity.setUserEntity(user); // 강사님이 추가하신 코드
        entity.setUserName(user.getUserName());
        entity.setUserEmail(user.getUserEmail());

        PostEntity savedPost = postRepository.save(entity);

        // hashtag 를 db에 저장
        List<String> hashTags = createDTO.getHashTags();

        // 해시태그 문자열 리스트에서 문자열들을 하나하나 추출한 뒤
        // 해시태그 엔터티로 만들고 그 엔터티를 데이터베이스에 저장한다.
        List<HashTagEntity> hashTagEntities = new ArrayList<>();
        for (String ht : hashTags) {
            HashTagEntity tagEntity = HashTagEntity.builder()
                    .postEntity(savedPost)
                    .tagName(ht)
                    .build();

            HashTagEntity savedTag = hashTagRepository.save(tagEntity);
            hashTagEntities.add(savedTag);
        }
        savedPost.setHashTags(hashTagEntities);

//        savedPost.getUser().addPost(savedPost);

        // 저장된 객체를 DTO 로 변환해서 반환
        return new PostDetailResponseDTO(savedPost);
    }


    /**
     * 수정 중간 처리
     */
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
        List<String> modHashTags = modifyDTO.getHashTags();
        boolean modStatus = modifyDTO.isStatus();

        if (modTitle != null) entity.setPostTitle(modTitle);
        if (modContent != null) entity.setPostContent(modContent);

        if (modStatus == entity.isStatus()) entity.setStatus(modStatus);

        PostEntity modifyPost = postRepository.save(entity);

        if (modHashTags != null) {
            hashTagRepository.deletePostId(postId); // 해시태그 삭제
            List<HashTagEntity> hashTagEntities = new ArrayList<>();
            for (String ht : modHashTags) {
                HashTagEntity tagEntity = HashTagEntity.builder()
                        .postEntity(modifyPost)
                        .tagName(ht)
                        .build();

                HashTagEntity savedTag = hashTagRepository.save(tagEntity);
                hashTagEntities.add(savedTag);
            }
            modifyPost.setHashTags(hashTagEntities);
        }

        return new PostDetailResponseDTO(modifyPost);
    }

    /**
     * 삭제 중간처리 ( 해시태그 삭제 후 -> 게시글 삭제 되어야함. 순서중요!)
     */
    public void delete(final String  postId)
            throws RuntimeException {
        commentRepository.deleteByPostId(postId);
        hashTagRepository.deletePostId(postId); // 해시태그 삭제
        postRepository.deleteById(postId); // 게시글 삭제
    }

    public void deleteUserId(final String  userId)
            throws RuntimeException {
        List<PostEntity> list = postRepository.getMyPostsList(userId);
        for (PostEntity postEntity : list) {
            delete(postEntity.getPostId());
        }
    }
}