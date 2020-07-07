package com.hgr.mini1.repository;

import com.hgr.mini1.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /* 이메일 조회 */
    UserEntity findByEmail(String email);

    /* 닉네임 조회 */
    UserEntity findByNickname(String nickname);

    /* 로그인 - 이메일과 비밀번호로 조회 */
    UserEntity findByEmailAndPwd(String email, String pwd);
}