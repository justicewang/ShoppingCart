package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Product;
import com.model.User;
import com.model.Wishlist;

public class WishlistDAO extends dbconnect{
	
	public int addWishlist(Wishlist w) throws SQLException {
		ps = con.prepareStatement("insert into wishlist(u_id,p_id) values(?,?)");
		ps.setInt(1,w.getU_id());
		ps.setInt(2,w.getP_id());
		int x =0;
		x = ps.executeUpdate();
		return x;
	}
	public int addWishlist(User u, Product p) throws SQLException {
		ps = con.prepareStatement("insert into wishlist(u_id,p_id) values(?,?)");
		ps.setInt(1,u.getId());
		ps.setInt(2,p.getId());
		int x =0;
		x = ps.executeUpdate();
		return x;
	}
	
	public int deleteWishlist(Wishlist w) throws SQLException {
		ps = con.prepareStatement("delete from wishlist where u_id=? and p_id=?");
		ps.setInt(1, w.getU_id());
		ps.setInt(2, w.getP_id());
		int x=0;
		x = ps.executeUpdate();
		return x;
	}
	
	public ArrayList<Wishlist> getAllWishlists() throws SQLException {
		ArrayList<Wishlist> wishlists = new ArrayList<Wishlist>();
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from wishlist");
		int u_id;
		int p_id;
		while (rs.next()) {
			u_id = rs.getInt(1);
			p_id = rs.getInt(2);
			Wishlist wish = new Wishlist(u_id,p_id);
			wishlists.add(wish);
		}
		return wishlists;
	}
}
