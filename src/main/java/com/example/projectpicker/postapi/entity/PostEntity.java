package com.example.projectpicker.postapi.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String postId;  // 게시판 식별

    @Column(nullable = false, name = "post_title")
    private String postTitle;   // 제목

    @Column(nullable = false, name = "post_content")
    private String postContent; // 내용


    private LocalDateTime createDate;   // 게시글 생성시간

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;   // 게시글 수정시간

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private String user;  // 회원 식별
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<HashTagEntity> hashTags = new ArrayList<>();

    @Column
    private boolean allow;

}