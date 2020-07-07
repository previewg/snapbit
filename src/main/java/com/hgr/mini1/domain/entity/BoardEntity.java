package com.hgr.mini1.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*파라미터가 없는 기본생성자를 추가하는 어노테이션 , JPA를 사용하기 위해
* 기본 생성자 생성은 필수적이라서*/
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public BoardEntity(Long id, String title, String content, String author, int hit, int recommend) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.recommend = recommend;
    }

}
