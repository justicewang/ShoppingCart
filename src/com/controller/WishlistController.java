package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Product;
import com.model.Wishlist;
import com.service.ProductService;
import com.service.WishlistService;

/**
 * Servlet implementation class WishlistController
 */
@WebServlet("/WishlistController")
public class WishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WishlistService w;
	private ProductService p;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishlistController() {
        super();
        w = new WishlistService();
        p = new ProductService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		HttpSession session  = request.getSession(true);
		if(action.equals("AddtoWish")) {
			int ProductId = Integer.parseInt(request.getParameter("id"));
			Integer u_id=(Integer) session.getAttribute("u_id");
			if(u_id==null) {
				forward = "login.jsp";
				session.setAttribute("not_login","please login...");
				response.sendRedirect(forward);
			}
			else {
				w.addwish(u_id, ProductId);
				p = new ProductService();
				Product p1 = p.getProductbyId(ProductId);
				session.setAttribute("add_wish_success", "product: "+p1.getName()+ " added to wishlist...");
				forward = "index.jsp";
				response.sendRedirect(forward);
			}
		}
		else if(action.equals("RemovefromWish")) {
			int ProductId = Integer.parseInt(request.getParameter("id"));
			Integer u_id=(Integer) session.getAttribute("u_id");
			if(u_id==null) {
				forward = "WishList.jsp";
				response.sendRedirect(forward);
			}
			else {
				Product p1 = p.getProductbyId(ProductId);
				w.deletewish(new Wishlist(u_id,ProductId));
				session.setAttribute("remove_from_wish_success", "product: "+p1.getName()+ " remove from wishlist...");
				forward = "WishList.jsp";
				response.sendRedirect(forward);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
