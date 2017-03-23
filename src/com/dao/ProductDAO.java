package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Product;


public class ProductDAO extends dbconnect{
	
	public int addProduct(Product p) throws SQLException {
		ps = con.prepareStatement("insert into product(id,name,price,description) values(?,?,?,?)");
		ps.setInt(1,p.getId());
		ps.setString(2,p.getName());
		ps.setDouble(3,p.getPrice());
		ps.setString(4,p.getDecription());
		int x =0;
		x = ps.executeUpdate();
		return x;
	}
	public int deleteProduct(Product p) throws SQLException {
		ps = con.prepareStatement("delete from product where id=?");
		ps.setInt(1, p.getId());
		int x=0;
		x = ps.executeUpdate();
		return x;
	}
	public int UpdateProduct(Product p) throws SQLException {
		ps = con.prepareStatement("update product set name=?, price=?, description=? where id=?");
		ps.setString(1, p.getName());
		ps.setDouble(2,p.getPrice());
		ps.setString(3, p.getDecription());
		ps.setInt(4,p.getId());
		int x = 0;
		x = ps.executeUpdate();
		return x;
	}
	public ArrayList<Product> getAllProducts() throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from product");
		int id;
		String name;
		double price;
		String description;
		while (rs.next()) {
			id = rs.getInt(1);
			name = rs.getString(2);
			price = rs.getDouble(3);
			description  = rs.getString(4);
			Product product = new Product(id,name,price,description);
			products.add(product);
		}
		return products;
	}
}
