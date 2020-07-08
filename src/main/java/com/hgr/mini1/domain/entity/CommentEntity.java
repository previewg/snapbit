package com.hgr.mini1.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity
@Table(name = "comment")
public class CommentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String comment;

    @Column(length = 500, nullable = false)
    private String nickname;

    @Builder
    public CommentEntity(Long id,String comment,String nickname) {
        this.id = id;
        this.comment = comment;
        this.nickname = nickname;
    }
}
