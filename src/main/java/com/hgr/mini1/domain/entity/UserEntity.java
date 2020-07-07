package com.hgr.mini1.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*파라미터가 없는 기본생성자를 추가하는 어노테이션 , JPA를 사용하기 위해
 * 기본 생성자 생성은 필수적이라서*/
@Getter
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

    @OneToMany(mappedBy = "userEntity")
    private Set<CommentEntity> commentEntities;

    @Builder
    public UserEntity(Long id, String email, String pwd, String name, String nickname) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
    }
}
