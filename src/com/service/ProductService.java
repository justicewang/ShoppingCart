package com.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.ProductDAO;
import com.model.Product;

public class ProductService {
	public ArrayList<Product> Products;
	int p_last_id;
	ProductDAO product_dao;
	public ProductService() {
		product_dao = new ProductDAO();
		try {
			Products = product_dao.getAllProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p_last_id = Products.get(Products.size()-1).getId();
	}
	public Product getProductbyId(int id) {
		for(Product p : Products) {
			if(p.getId()==id)
				return p;
		}
		return null;
	}
	public int addproduct(String name,double price, String description) {
		int x = 0;
		Product p = new Product(++p_last_id,name,price,description);
			Products.add(p);
			try {
				x = product_dao.addProduct(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return x;
	}
	
	public int addproduct(Product p) {
		int x = 0;
			Products.add(p);
			try {
				x = product_dao.addProduct(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return x;
	}
	
	public int deleteproduct(Product p) {
		int x = 0;
		try {
			x = product_dao.deleteProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int updateproduct(Product p,String name,double price, String description) {
		int x = 0;
		for(Product p1 : Products) {
			if(p1.getId()==p.getId()){
				p1.setName(name);;
				p1.setPrice(price);
				p1.setDecription(description);
				try {
					product_dao.UpdateProduct(p);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return x;
	}
}
