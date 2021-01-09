package com.contact.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contact.entity.Contact;
import com.contact.entity.Users;
import com.contact.repository.UsersRepository;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;
	Users users;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {

		String username = principal.getName();
		if (users == null) {
			users = usersRepository.getUserByName(username);
		}
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

	@PostMapping("/process-contact")
	public String processContactFrom(@ModelAttribute Contact contact, @RequestParam("image1") MultipartFile file, Principal principal) {

		try {
			
			if (users == null) {
				String username = principal.getName();
				users = usersRepository.getUserByName(username);
			}
			
			
			if(file.isEmpty())
			{
				throw new Exception("Please Upload Image");
				
			}else
			{
				
			    String UPLOAD_DIR1=new ClassPathResource("static/upload").getFile().getAbsolutePath().toString();
				String uploadloc=UPLOAD_DIR1+"\\"+file.getOriginalFilename();
				
				
				//File imageFile=new ClassPathResource("C:\\eclipse-new-workspace\\spring-contact-app\\src\\main\\resources\\static\\upload").getFile();
				Path path= Paths.get(uploadloc);
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				contact.setImage(file.getOriginalFilename());
			}
			
			
			
			users.getContacts().add(contact);
			usersRepository.save(users);

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace().toString());
			
		}
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
