package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.LoginForm;
import com.example.service.UserService;

@Controller	
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "login";
	}
	
	/**
	 * ログインするため.
	 * @param email
	 * @return
	 */
	@RequestMapping("/enter")
	public String enter(@Validated LoginForm form, BindingResult result) {
		
		if(form.getEmail() == null || form.getPassword() == null) {
			result.rejectValue("error", null, "パスワードまたはメールアドレスが間違っています");
			return index();
		}
		
		if(result.hasErrors()) {
			return index();
		}
		userService.findByMailAddress(form.getEmail(), form.getPassword());
		return "forward:/item/itemList";
	}

}
