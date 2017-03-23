package com.model;

public class Wishlist {
	private int u_id;
	private int p_id;
	
	public Wishlist(){
		
	}
	public Wishlist(int u_id, int p_id) {
		this.u_id = u_id;
		this.p_id = p_id;
	}
	/**
	 * @return the u_id
	 */
	public int getU_id() {
		return u_id;
	}
	/**
	 * @param u_id the u_id to set
	 */
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	/**
	 * @return the p_id
	 */
	public int getP_id() {
		return p_id;
	}
	/**
	 * @param p_id the p_id to set
	 */
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
}
