package com.contact.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact.entity.Users;
import com.contact.helper.Message;
import com.contact.repository.UsersRepository;

@Controller
public class HomeController {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

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
	public String formRegister(@Valid @ModelAttribute("user") Users users, BindingResult bindingResult,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {

		try {

			if (bindingResult.hasErrors()) {
				System.out.println("Error :" + bindingResult.toString());
				model.addAttribute("user", users);
				return "signup";
			}

			if (!agreement) {
				System.out.println("You Do not agree Term And conditions");
				throw new Exception("You Do not agree Term And conditions");
			}

			users.setRole("ROLE_USER");
			users.setStatus(true);
			users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

			System.out.println(agreement);
			System.out.println(users);
			model.addAttribute("title", "Register-Smart Contact Manager");
			Users results = usersRepository.save(users);
			model.addAttribute("user", new Users());
			session.setAttribute("message", new Message("Sucessfully Registered !!", "alert-success"));
			return "signup";

		} catch (Exception e) {
			model.addAttribute("user", users);
			session.setAttribute("message", new Message("Something went Wrong !!" + e.getMessage(), "alert-danger"));
			return "signup";
		}

	}
}
