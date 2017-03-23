package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class dbconnect {
	Connection con;
	PreparedStatement ps;
	Statement st;
	public dbconnect() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/shopping_cart","root","");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
