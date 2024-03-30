package com.sashikanta.ims.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sashikanta.ims.entities.User;
import com.sashikanta.ims.repos.UserRepository;
import com.sashikanta.ims.util.UserRole;



@Controller
public class UserController {

	public static boolean isvalidate = false;

	@Autowired
	public UserRepository userRepository;

	@RequestMapping("/registerUser")
	public String register(@ModelAttribute("user") User user, @RequestParam("role") UserRole role) {
		user.setRole(role);
		userRepository.save(user);
		return "login-register.html";
	}

	@RequestMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap, RedirectAttributes redirectAttributes) {
		List<User> users = userRepository.findByEmail(email);

		if (users.size() == 1 && users.get(0).getPassword().equals(password)) {
			isvalidate = true;
			redirectAttributes.addFlashAttribute("successMessage", "Login successful!");
			User user = users.get(0);
			if (user.getRole() == UserRole.OWNER) {
				return "redirect:/owner-dashboard";
			} else if (user.getRole() == UserRole.MANAGER) {
				return "redirect:/home";
			}
		} else {
			modelMap.addAttribute("msg", "Invalid username or password. Please try again.");
		}
		return "login-register.html";
	}

	@RequestMapping("/login-register")
	public String logout() {
		isvalidate = false;
		return "login-register.html";
	}
}