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

//    @Column(name = "post_id")
//    private String post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", insertable = true, updatable = false)
    private PostEntity hashtag;
}
