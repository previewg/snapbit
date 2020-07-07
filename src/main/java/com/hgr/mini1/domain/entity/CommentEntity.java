package com.hgr.mini1.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @Builder
    public CommentEntity(Long id, BoardEntity boardEntity, UserEntity userEntity, String comment) {
        this.id=id;
        this.boardEntity=boardEntity;
        this.userEntity=userEntity;
        this.comment=comment;
    }
}
