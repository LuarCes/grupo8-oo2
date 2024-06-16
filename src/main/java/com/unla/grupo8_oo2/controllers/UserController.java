package com.unla.grupo8_oo2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo8_oo2.helpers.ViewRouteHelper;

@Controller
public class UserController {

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	/*@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}*/

	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}
}
 
