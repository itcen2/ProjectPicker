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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String tagId;

    private String tagName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id")
    private String post;
}
