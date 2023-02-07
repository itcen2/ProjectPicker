package com.example.projectpicker.postapi.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "tagId")
@Builder

@Entity
@Table(name = "tbl_hashtag")
public class HashTagEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid" )
    private String tagId; // 태그 식별코드

    private String tagName; // 태그 이름


    // 게시판 와 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",insertable = false,updatable = false)
    private PostEntity post;

    // 해시태그 추가,수정시 사용할 외래키(post_id)
    @Column(name = "post_id")
    private String postId;



}
