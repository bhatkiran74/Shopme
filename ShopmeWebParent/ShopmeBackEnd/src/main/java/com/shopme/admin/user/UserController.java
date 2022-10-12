package com.shopme.admin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = userService.listAll();
		
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		
		List<Role> listRoles = userService.listRole();
		
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully...");
		System.out.println(user);
		userService.save(user);
		return "redirect:/users";
	}
	
	
	
	
}