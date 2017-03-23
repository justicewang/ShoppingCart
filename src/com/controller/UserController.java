package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Product;
import com.service.UserService;

/**
 * Servlet implementation class Controller
 */

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService u;
	
    public UserController() {
        super();
        //new dbconnect();
        u = new UserService();
        //System.out.println("loading");
    }
//    public void init() throws ServletException {
//    // TODO Auto-generated method stub
//    super.init();
//    //new dbconnect();
//    u = new UserService();
//    System.out.println("loading");
//    for(int i = 0; i < 10; i++){
//    System.out.println(i + "loading");
//    }
//    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		HttpSession session  = request.getSession(true);
		if(action.equals("logout")) {
			session.setAttribute("u_id", null);
			forward = "index.jsp";
			response.sendRedirect(forward);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String forward ="";
		HttpSession session  = request.getSession(true);
		//String path = request.getContextPath();
		int u_id = u.validate(username, password);
		if( request.getParameter("register")!=null){
			if(u_id<0){
				 session.setAttribute("u_id", u.getLastId());
				 u.adduser(username, password);
				 ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
				 if(cart!=null) {
					 session.setAttribute("cart"+u_id, cart);
					 session.setAttribute("cart", null);
				 }
				 forward = "index.jsp";
			}
			else{
				forward = "register.jsp";
				//user already exist
			}
		}
		if( request.getParameter("login")!=null){
			if(u_id>=0) {
				 session.setAttribute("u_id", u_id);
				 ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
				 if(cart!=null){
					 ArrayList<Product> Current_user_cart = (ArrayList<Product>) session.getAttribute("cart"+u_id);
					 if(Current_user_cart==null) {
						 session.setAttribute("cart"+u_id, cart);
						 session.setAttribute("cart", null);
					 }
					 else {
						 Current_user_cart.addAll(cart);
						 session.setAttribute("cart"+u_id, Current_user_cart);
					 }
					 forward = "index.jsp";
				 }
				//response.sendRedirect(path+"/index.jsp");
			}
			else {
				//response.sendRedirect(path+"/login.jsp");
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

}
