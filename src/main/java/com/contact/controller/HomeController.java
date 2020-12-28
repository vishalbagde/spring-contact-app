package com.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact.entity.Users;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-Smart Contact Manager");
		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register-Smart Contact Manager");
		model.addAttribute("user", new Users());
		return "signup";
	}

	// form_register
	@PostMapping("/form_register")
	public String formRegister(@ModelAttribute("user") Users users,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model) {
		
		if(!agreement)
		{
			System.out.println("You Do not agree Term And conditions");
		}
		
		users.setRole("user");
		users.setStatus(true);
		
		System.out.println(agreement);
		System.out.println(users);
		model.addAttribute("title", "Register-Smart Contact Manager");
		model.addAttribute("user", users);
		return "signup";
	}
}
