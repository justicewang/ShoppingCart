package com.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.dao.UserDAO;
import com.model.User;

public class UserService {
	ArrayList<User> Users;
	int u_last_id;
	UserDAO user_dao;
	public UserService() {
		user_dao = new UserDAO();
		try {
			Users = user_dao.getAllUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u_last_id = Users.get(Users.size()-1).getId();
	}
	public int getLastId() {
		return u_last_id;
	}
	//check is the username is exist
	public boolean is_name_exist(String name) {
		for(User u : Users) {
			if(u.getUsername().equals(name))
				return true;
		}
		return false;
	}
	
	public int validate(String name, String password) {
		int x = -1;
		for(User u : Users) {
			if(u.getUsername().equals(name) && u.getPassword().equals(password))
				return u.getId();
		}
		return -1;
	}
	
	public int adduser(String name, String password) {
		int x = 0;
		if(!is_name_exist(name)) {
			User u = new User(++u_last_id,name,password);
			Users.add(u);
			try {
				x = user_dao.addUser(u);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return x;
	}
	
	public int deleteuser(User u) {
		int x = 0;
		try {
			x = user_dao.deleteUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int updateuser(User u, String name, String password) {
		int x = 0;
		if(!is_name_exist(name)) {
			for(User user : Users) {
				if(user.getId()==u.getId()){
					u.setUsername(name);
					u.setPassword(password);
					try {
						user_dao.UpdateUser(u);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return x;
	}
	
}
