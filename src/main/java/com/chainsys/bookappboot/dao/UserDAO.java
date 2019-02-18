package com.chainsys.bookappboot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.bookappboot.model.User;

@Repository
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insert(User user) {
		String query = "INSERT INTO users(id,name,email,password) VALUES(users_id_seq.nextval,?,?,?)";
		Object[] params = new Object[] { user.name, user.email, user.password };
		int rows = jdbcTemplate.update(query, params);
		System.out.println(rows);
	}

	public void update(User user) {
		String query = "UPDATE users SET name=?,email=?,password=? WHERE id=?";
		Object[] params = new Object[] { user.name, user.email, user.password, user.id };
		int rows = jdbcTemplate.update(query, params);
		System.out.println(rows);
	}

	public void delete(int id) {
		String query = "DELETE FROM users WHERE id=?";
		int rows = jdbcTemplate.update(query, id);
		System.out.println(rows);

	}

	public User findById(int id) {
		String query = "SELECT id,name,email,password from users WHERE id=?";
		Object[] params = new Object[] { id };
		User user = jdbcTemplate.queryForObject(query, params, (rs, row) -> {
			User u = convert(rs);
			return u;
		});
		return user;
	}

	public User convert(ResultSet rs) throws SQLException {
		User user = new User();
		user.id = rs.getInt("id");
		user.name = rs.getString("name");
		user.email = rs.getString("email");
		user.password = rs.getString("password");
		return user;

	}

	public List<User> findAll() {
		String query = "SELECT id,name,email,password from users";
		List<User> user = jdbcTemplate.query(query, (rs, row) -> {
			User u = convert(rs);
			return u;
		});
		return user;

	}

	public User login(String email, String password) {
		String query = "SELECT id,name,email,password from users WHERE email=? and password=?";
		Object[] params = new Object[] { email, password };
		User user = jdbcTemplate.queryForObject(query, params, (rs, row) -> {
			User u = convert(rs);
			return u;
		});
		return user;

	}
}
