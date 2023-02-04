package com.example.projectpicker.postapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postId")
@Builder

@Entity
@Table(name = "tbl_board")
public class PostEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // 전략 = "중복되지 않는 id" 설정

    private Long postId;
    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime create_date;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user")

}
