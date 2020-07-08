package com.hgr.mini1.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity
@Table(name = "comment")
public class CommentEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String comment;

    @Builder
    public CommentEntity(Long id,String comment) {
        this.id=id;
        this.comment=comment;
    }
}
