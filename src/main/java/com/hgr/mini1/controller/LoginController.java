
package com.hgr.mini1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgr.mini1.model.User;
import com.hgr.mini1.repository.UserRepository;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Controller
@Data
public class LoginController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession session;

	@GetMapping("/signIn")
	public String signIn() {
		return "signIn";
	}

	@PostMapping("/signIn")
	@ResponseBody
	public Map<String, String> signInPost(User user) {
		User dbUser = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		Map<String,String> result = new HashMap<>();

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

	@PostMapping("/signUp")
	@ResponseBody
	public String signUpPost(User user) {
		userRepository.save(user);
		return "success";
	}

	@PostMapping("/signUpVerify")
	@ResponseBody
	public String signUpVerify(User user) {
		User result = userRepository.findByEmail(user.getEmail());
		String verified = "";
		if (result == null) {
			verified = "isAvailable";
		} else {
			verified = "isExisted";
		}
		return verified;
	}

	@GetMapping("/signOut")
	@ResponseBody
	public String signOut() {
		session.removeAttribute("user_info");
		return "success";
	}
}
 