package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.UserForm;
import com.example.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "register_user";
	}
	
	/**
	 * 登録画面で登録する.
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@Validated UserForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return index();
		}
		userService.insert(form);
		return "login";
	}

}
