package com.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.repository.UsersRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UsersRepository usersRepository;

	@RequestMapping("/index")
	public String dashboard() {

		System.out.println("inde call");
		return "user/user_dashboard";
	}

}
