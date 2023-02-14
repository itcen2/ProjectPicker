package com.example.projectpicker.user.dto;


import com.example.projectpicker.post.dto.response.PostCommentDetailResponseDTO;
import com.example.projectpicker.post.dto.response.PostListDataResponseDTO;
import com.example.projectpicker.post.entity.PostEntity;
import com.example.projectpicker.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

// 로그인시 클라이언트에게 응답해주는 객체 ( 클라이언트 <---- DB)
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MypageResponseDTO {
    private String userId;
    private String email; // 이메일
    private String userName; // 회원 이름

    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate joinDate; // 가입 날짜

    private List<PostListDataResponseDTO> posts;

    // 엔티티를 DTO로 변경
    public MypageResponseDTO(UserEntity userEntity, List<PostListDataResponseDTO> posts){
        this.userId = userEntity.getUserId();
        this.email = userEntity.getUserEmail(); // 이메일  -- DB 이메일 정보를 가져와서 클라이언트에게 응답 전달하는 dto에 넣어준다.
        this.userName = userEntity.getUserName(); // 회원 이름
        this.joinDate = LocalDate.from(userEntity.getJoinDate()); // 가입 날짜
        this.posts = posts;
    }
}
