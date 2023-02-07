package com.example.projectpicker.admin.main.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "post")
@EqualsAndHashCode(exclude = "postId")
public class MainPostEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "post_id")
    private String postId;

    @Size(min = 5, max = 30)
    @Column(nullable = false, name = "post_title")
    private String postTitle;

    @Column(nullable = false, name = "post_content")
    private String postContent;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime modifyDate;

    private Boolean allow;
}
