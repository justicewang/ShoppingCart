package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Product;
import com.service.ProductService;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService p;
	private WishlistController w;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        p = new ProductService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
		HttpSession session  = request.getSession(true);
		if(action.equals("AddtoCart")) {
			int ProductId = Integer.parseInt(request.getParameter("id"));
			ArrayList<Product> cart;
			Integer u_id = (Integer) session.getAttribute("u_id");
			Product p1 = p.getProductbyId(ProductId);
			if(u_id==null) {
				cart = (ArrayList<Product>) session.getAttribute ("cart");
				if (cart==null)
					cart = new ArrayList<Product>();
				cart.add(p1);
				session.setAttribute("cart", cart);
			}
			else {
				cart = (ArrayList<Product>) session.getAttribute ("cart"+u_id);
				if (cart==null)
					cart = new ArrayList<Product>();
				cart.add(p1);
				session.setAttribute("cart"+u_id, cart);
			}
			session.setAttribute("add_cart_success", "product: "+p1.getName()+ " added to cart...");
			forward = "index.jsp";
			response.sendRedirect(forward);
		}
		else if(action.equals("WishAddtoCart")) {
			int ProductId = Integer.parseInt(request.getParameter("id"));
			Integer u_id = (Integer) session.getAttribute("u_id");
			ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute ("cart"+u_id);
			if (cart==null)
				cart = new ArrayList<Product>();
			Product p1 = p.getProductbyId(ProductId);
			cart.add(p1);
			session.setAttribute("cart"+u_id, cart);
			session.setAttribute("wish_add_to_cart_success", "product: "+p1.getName()+ " added to cart...");
			forward = "WishList.jsp";
			response.sendRedirect(forward);
		}
		else if(action.equals("RemovefromCart")) {
			int ProductId = Integer.parseInt(request.getParameter("id"));
			Integer u_id = (Integer) session.getAttribute("u_id");
			ArrayList<Product> cart;
			if(u_id==null)
				cart = (ArrayList<Product>) session.getAttribute ("cart");
			else
				cart = (ArrayList<Product>) session.getAttribute ("cart"+u_id);
			if (cart==null){
				forward = "Cart.jsp";
				response.sendRedirect(forward);
			}
			Product p1 = p.getProductbyId(ProductId);
			cart.remove(p1);
			if(cart.isEmpty())
				cart = null;
			if(u_id==null)
				session.setAttribute("cart", cart);
			else
				session.setAttribute("cart"+u_id, cart);
			session.setAttribute("remove_from_cart_success", "product: "+p1.getName()+ " remove from cart...");
			forward = "Cart.jsp";
			response.sendRedirect(forward);
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
