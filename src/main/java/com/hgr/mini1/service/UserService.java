package com.hgr.mini1.service;

import com.hgr.mini1.domain.entity.UserEntity;
import com.hgr.mini1.dto.BoardDto;
import com.hgr.mini1.dto.UserDto;
import com.hgr.mini1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {
    public UserRepository userRepository;

    @Autowired
    HttpSession session;


    public Map<String,String> signIn(UserDto userDto){
        Map<String,String> result = new HashMap<>();
        UserEntity dbUser = userRepository.findByEmailAndPwd(userDto.toEntity().getEmail(),userDto.toEntity().getPwd());

        if (dbUser != null) {
            session.setAttribute("user_info", dbUser);
            result.put("verified","correct");
            result.put("username",dbUser.getName());
        }else{
            result.put("verified","incorrect");
            result.put("username","");
        }
        return result;
    }

    public void signUp(UserDto userDto){
        userRepository.save(userDto.toEntity());
    }

    public String emailVerify(UserDto userDto){
        UserEntity result = userRepository.findByEmail(userDto.toEntity().getEmail());
        String verified;
        if (result == null) {
            verified = "isAvailable";
        } else {
            verified = "isExisted";
        }
        return verified;
    }

    public String nicknameVerify(UserDto userDto){
        UserEntity result = userRepository.findByNickname(userDto.toEntity().getNickname());
        String verified;
        if (result == null) {
            verified = "isAvailable";
        } else {
            verified = "isExisted";
        }
        return verified;
    }

    public void signOut(){
        session.removeAttribute("user_info");
    }

    public void userDelete(UserEntity userDto){
        System.out.println(userDto);
        session.removeAttribute("user_info");
        userRepository.deleteById(userDto.getId());
    }

    @Transactional
    public void userUpdate(UserDto userDto) {
        userRepository.save(userDto.toEntity());
    }
}
