package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.form.UserForm;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 登録画面でinsertするためのサービス.
	 * @param form
	 */
	public void insert(UserForm form) {
		User user = new User();
		user.setAddress(form.getAddress());
		user.setEmail(form.getEmail());
		user.setName(form.getName());
		user.setPassword(form.getPassword());
		user.setTelephone(form.getTelephone());
		user.setZipcode(form.getZipcode());
		userRepository.insert(user);
	}
	
	/**
	 * ログインするために1件検索するサービス.
	 * @param email
	 * @return
	 */
	public User findByMailAddress(String email, String password) {
		User user = userRepository.findByMailAddress(email, password);
		return user;
	}

}
