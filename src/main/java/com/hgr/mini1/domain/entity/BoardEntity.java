package com.hgr.mini1.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ColumnDefault("0")
    private int hit;
    private int recommend;

    @OneToMany(fetch =FetchType.EAGER)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    @Builder
    public BoardEntity(Long id,String title, String content, String author, int hit, int recommend) {
        this.id=id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.recommend = recommend;
    }

}
