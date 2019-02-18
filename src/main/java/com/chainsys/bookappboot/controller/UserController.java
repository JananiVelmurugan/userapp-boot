package com.chainsys.bookappboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.bookappboot.dao.UserDAO;
import com.chainsys.bookappboot.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	public UserDAO dao;

	@GetMapping("/save")
	public String insert(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		System.out.println("insert called");
		User user = new User();
		user.name = name;
		user.email = email;
		user.password = password;
		dao.insert(user);
		return "called insert";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println("update called");
		User user = new User();
		user.id = id;
		user.name = name;
		user.email = email;
		user.password = password;
		dao.update(user);
		return "called update";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		System.out.println("delete called");
		dao.delete(id);
		return "called delete";
	}

	@GetMapping("/login")
	public User login(@RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println("login called");
		User user = dao.login(email, password);
		return user;
	}

	@GetMapping("/")
	public List<User> findAll() {
		return dao.findAll();
	}

}
