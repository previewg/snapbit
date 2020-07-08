package com.hgr.mini1.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String pwd;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String nickname;

//    @OneToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @Builder
    public UserEntity(Long id,String email, String pwd, String name, String nickname) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
    }
}
