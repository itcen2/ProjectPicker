package com.example.projectpicker.post.entity;

import com.example.projectpicker.user.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "tbl_comment")
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy ="uuid" )
    private String commentId; // 댓글 식별 코드(아이디)

    private String comment; // 댓글 내용

    private LocalDateTime createAt; // 댓글 작성 시간



    // 회원과 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    // 댓글 추가, 수정시 사용할 외래키
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_name")
    private String userName;




    // 게시판와 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private PostEntity post;

    // 댓글 추가, 수정시 사용할 외래키
    @Column(name = "post_id")
    private String postId;
}
