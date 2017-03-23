package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.User;

public class UserDAO extends dbconnect{
	
	public int addUser(User u) throws SQLException {
		ps = con.prepareStatement("insert into user(id,name,password) values(?,?,?)");
		ps.setInt(1,u.getId());
		ps.setString(2,u.getUsername());
		ps.setString(3,u.getPassword());
		int x = 0;
		x = ps.executeUpdate();
		return x;
	}
	public int deleteUser(User u) throws SQLException {
		ps = con.prepareStatement("delete from user where id=?");
		ps.setInt(1, u.getId());
		int x=0;
		x = ps.executeUpdate();
		return x;
	}
	public int UpdateUser(User u) throws SQLException {
		ps = con.prepareStatement("update user set name=?, password=? where id=?");
		ps.setString(1,u.getUsername());
		ps.setString(2,u.getPassword());
		ps.setInt(3,u.getId());
		int x = 0;
		x = ps.executeUpdate();
		return x;
	}
	public ArrayList<User> getAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from user");
		int id;
		String username;
		String password;
		while (rs.next()) {
			id = rs.getInt(1);
			username = rs.getString(2);
			password  = rs.getString(3);
			User user = new User(id,username,password);
			users.add(user);
		}
		return users;
	}
}
