package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setTelephone(rs.getString("telephone"));
		user.setZipcode(rs.getString("zipcode"));
		return user;
	};
	
	/**
	 * 登録画面でinsertするためのリポジトリ.
	 * @param user
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(address, email, name, password, telephone, zipcode) VALUES(:address, :email, :name, :password, :telephone, :zipcode)";
		template.update(sql, param);
	}
	
	/**
	 * ログインするために1件検索するリポジトリ.
	 * @param email
	 * @return
	 */
	public User findByMailAddress(String email, String password) {
		String sql = "SELECT id, address, email, name, password, telephone, zipcode FROM users WHERE email = :email AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

}
