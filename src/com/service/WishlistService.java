package com.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.WishlistDAO;
import com.model.Product;
import com.model.Wishlist;

public class WishlistService {
	ArrayList<Wishlist> Wishlists;
	WishlistDAO wishlist_dao;
	ProductService ps;
	public WishlistService() {
		wishlist_dao = new WishlistDAO();
		ps = new ProductService();
		try {
			Wishlists = wishlist_dao.getAllWishlists();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> getWishlistById(int u_id){
		ArrayList<Product> wish = new ArrayList<Product>();
		for(Wishlist w : Wishlists) {
			if(w.getU_id()==u_id) {
				int p_id = w.getP_id();
				wish.add(ps.getProductbyId(p_id));
			}
		}
		return wish;
	}
	
	public boolean is_wish_exist(int u_id, int p_id) {
		for( Wishlist w : Wishlists) {
			if(w.getU_id()==u_id && w.getP_id()==p_id)
				return true;
		}
		return false;
	}
	
	public int addwish(int u_id,int p_id) {
		int x = 0;
		if(!is_wish_exist(u_id,p_id)) {
			Wishlist w = new Wishlist(u_id,p_id);
			Wishlists.add(w);
			try {
				x = wishlist_dao.addWishlist(w);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return x;
	}
	
	public int deletewish(Wishlist w) {
		int x = 0;
		try {
			x = wishlist_dao.deleteWishlist(w);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
}
