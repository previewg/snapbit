
package com.hgr.mini1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgr.mini1.model.User;
import com.hgr.mini1.repository.UserRepository;

import lombok.Data;

@Controller
@Data

public class LoginController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	HttpSession session;

	@PostMapping("/id_check")
	@ResponseBody
	public String idCheck(@ModelAttribute User user, Model model) {
		User result = userRepository.findByEmail(user.getEmail());
		String msg = "";
		if (result == null) {
			msg = "1";

		} else {
			msg = "0";

		}
		return msg;

	}

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user, Model model) {
		userRepository.save(user);
		return "signin";
	}

	@GetMapping("/signout")
	public String signout() {
		session.removeAttribute("user_info");
		return "redirect:/";
	}

	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user) {
		User dbUser = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		if (dbUser != null) {
			session.setAttribute("user_info", dbUser);
		}
		return "redirect:/";
	}

}
