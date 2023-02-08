package com.example.projectpicker.post.entity;

import com.example.projectpicker.user.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postId")
@Builder

@Entity
@Table(name = "tbl_post")
public class PostEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // 전략 = "중복되지 않는 id" 설정
    private String postId; //게시판 식별

    @Column(nullable = false, name = "post_title") // 널값 허용X
    private String postTitle; // 게시글 제목

    @Column(nullable = false, name = "post_content")
    private String postContent; // 게시글 내용

    @CreationTimestamp // INSERT 시점에 서버시간을 자동으로 입력
//    @Column(name = "create_date")
    private LocalDateTime createDate; // 게시글 생성시간

//    @UpdateTimestamp // UPDATE 시점에 서버시간을 자동으로 입력
    @CreationTimestamp
    @Column(name = "modify_date")
    private LocalDateTime modifyDate; // 게시글 수정 시간


    /**
     회원 와 관계 설정
     *
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false,updatable = false)
    private UserEntity user;

    // 회원 외래키
    @Column(name = "user_id")
    private String userId; // 회원 식별

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_name")
    private String userName;


    /**
     * 해시태그와 관계형 매핑
      */

    @OneToMany(mappedBy = "post")
    private List<HashTagEntity> hashTags = new ArrayList<>(); //해시태그 목록


    // 일반 회원이 게시글 등록시, 관리자가 게시글 등록 허용여부에 사용되는 엔티티
    @Column
    private boolean allow;
}
