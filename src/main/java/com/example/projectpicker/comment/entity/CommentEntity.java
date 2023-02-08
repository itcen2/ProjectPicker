package com.example.projectpicker.comment.entity;

import com.example.projectpicker.postapi.entity.PostEntity;
import com.example.projectpicker.user.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "commentId")
@Entity
@Builder
@Table(name = "tbl_comment")
public class CommentEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String commentId;
    private String comment;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "usr_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "usr_name", referencedColumnName = "user_name"),
            @JoinColumn(name = "usr_email", referencedColumnName = "user_email")
    })
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity comments;

}
