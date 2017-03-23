package com.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class generate_db {
	private Connection con;
	private Statement st;
	public generate_db() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/shopping_cart","root","");
			if(con != null){
				System.out.println("jdbc connected...");
				st = con.createStatement();
				
				DatabaseMetaData dbm = con.getMetaData();
				ResultSet tables = dbm.getTables(null, null, "wishlist", null);
				if (tables.next()) {
				   st.execute("drop table wishlist");
				}
				tables = dbm.getTables(null, null, "product", null);
				if (tables.next()) {
				   st.execute("drop table product");
				}
				tables = dbm.getTables(null, null, "user", null);
				if (tables.next()) {
				   st.execute("drop table user");
				}
				StringBuffer sb = new StringBuffer();
				String read;
				try {
					FileReader fr = new FileReader(new File("/Users/justicewang/Documents/workspace/shoppingcart/generate_db.sql"));
					BufferedReader br = new BufferedReader(fr);
					while((read = br.readLine())!=null) {
						if(read.length()>=2 && read.substring(0, 2).equals("--"))
							continue;
						sb.append(read+"\n");
					}
					br.close();
					//System.out.println(sb.toString());
					String[] sqlList = sb.toString().split(";");
					for(String s : sqlList) {
						if(!s.trim().equals(""))
							st.execute(s);
					}
					System.out.println("database is created..");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
