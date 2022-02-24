package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private int userCount = 3;
	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "Rose", new Date()));
		users.add(new User(3, "Jill", new Date()));
	}

	// get all users
	public List<User> allUsers() {
		return users;
	}
	
	// get one user
	public User oneUser(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	// create new user
	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	// delete one user
	public User deleteUser(int id) {
		Iterator<User> iterUser = users.iterator();
		while(iterUser.hasNext()) {
			User nextUser = iterUser.next();
			if (nextUser.getId() == id) {
				iterUser.remove();
				return nextUser;
			}
		}
		return null;
	}



}


































