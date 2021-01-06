package com.contact.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contact.entity.Contact;
import com.contact.entity.Users;
import com.contact.repository.UsersRepository;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		
		String username = principal.getName();
		Users users = usersRepository.getUserByName(username);
		model.addAttribute("user", users);
	}

	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		return "user/user_dashboard";
	}

	@GetMapping("/add-contact")
	public String openContactFrom(Model model) {

		model.addAttribute("title", "Register-Smart Contact Manager");
		model.addAttribute("contact", new Contact());
		return "user/add_contact_form";
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() {

		Users users = new Users();
		users.setName("vishal3");
		users.setEmail("vishal9@gmail.com");
		users.setPassword("vishal");
		users.setAbout("this is test users");
		users.setRole("ROLE_USER");
		users.setUserName("vbagde9");

		Contact contact = new Contact();
		contact.setFirstName("test");

		users.getContacts().add(contact);
		usersRepository.save(users);
		return "working";
	}

}
