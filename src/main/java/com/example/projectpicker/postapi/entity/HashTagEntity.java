package com.example.projectpicker.postapi.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "tbl_hashtag") // 테이블(해시태그) 이름
public class HashTagEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid" ) // 해시태그 테이블 기본키
    private String tagId; // 태그 식별코드

    private String tagName; // 태그 이름


    // 게시판 와 관계 설정 ( 다 대 일 관계 / [게시판] ---<- [해시태그] )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",insertable = false,updatable = false) // join 할 테이블(게시판)의 기본 키 이름
    private PostEntity post;

    // 해시태그 추가,수정시 사용할 외래키(post_id)
    @Column(name = "post_id")
    private String postId;



}
