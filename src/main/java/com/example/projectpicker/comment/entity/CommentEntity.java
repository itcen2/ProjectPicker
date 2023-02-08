package com.example.projectpicker.comment.entity;

import com.example.projectpicker.post.entity.PostEntity;
import com.example.projectpicker.user.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "tbl_comment")
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "comment_id")
    private String commentId;  // 댓글 식별

    @Column(nullable = false, name = "comment")
    private String comment;  // 댓글 내용

    @CreationTimestamp
    private LocalDateTime createAt;  // 댓글 작성 시간

    @UpdateTimestamp
    private LocalDateTime modifyAt;  // 댓글 수정 시간

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;



}
