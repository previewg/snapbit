package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.BoardEntity;
import com.hgr.mini1.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String pwd;
    private String name;
    private String nickname;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .email(email)
                .pwd(pwd)
                .name(name)
                .nickname(nickname)
                .build();
        return userEntity;
    }

    @Builder
    public UserDto(Long id,String email, String pwd, String name, String nickname) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
    }
}
