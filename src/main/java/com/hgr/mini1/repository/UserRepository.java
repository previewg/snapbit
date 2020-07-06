package com.hgr.mini1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hgr.mini1.model.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    /* 이메일 조회 */
    public User findByEmail(String email);

    /* 닉네임 조회 */
    public User findByNickname(String nickname);

    /* 로그인 - 이메일과 비밀번호로 조회 */
    public User findByEmailAndPwd(String email, String pwd);
}