package com.contact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contact.entity.Contact;
import com.contact.entity.Users;
import com.contact.repository.UsersRepository;

@Controller
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		
		Users users=new Users();
		users.setName("vishal");
		users.setEmail("vishal2@gmail.com");
		users.setPassword("vishal");
		users.setAbout("this is test users");
		users.setRole("admin");
		users.setUserName("vbagde2");
		
		Contact contact =new Contact();
		contact.setFirstName("test");
	
		users.getContacts().add(contact);
		usersRepository.save(users);
		
		Users u1=usersRepository.getOne(1);
		
		
		List<Contact> myList=u1.getContacts();
		System.out.println(myList);
		
		
		return "working";
	}
	

}
