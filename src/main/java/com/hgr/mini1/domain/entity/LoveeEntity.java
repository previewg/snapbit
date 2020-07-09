package com.hgr.mini1.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity
@Table(name = "lovee")
public class LoveeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "love_id")
    private Long id;

    @Column(nullable = false,name = "user_id")
    private Long user;

    @Column(nullable = false,name = "board_id")
    private Long board;

    @Builder
    public LoveeEntity(Long id, Long user,Long board) {
        this.id=id;
        this.user = user;
        this.board = board;
    }
}
