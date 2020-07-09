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

    @Column(length = 100, nullable = false,name = "user_id")
    private String user;

    @Column(length = 100, nullable = false,name = "board_id")
    private String board;

    @Builder
    public LoveeEntity(Long id, String user,String board) {
        this.id=id;
        this.user = user;
        this.board = board;
    }
}
