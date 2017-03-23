<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script data-require="jquery@*" data-semver="3.1.1"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="Script/script.js"></script>
<link rel="stylesheet" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
</head>
<body><nav class="navbar navbar-default">
   		<div class="container">
   			<div class="navbar-header">
   				<a href="index.jsp" class="navbar-brand logo">
                    <img src="img/DBL.png" alt="DBL_LOGO" style="height:100%; width:auto">
                </a>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
   			</div>
            <div class="collapse navbar-collapse" id="navbar-collapse" >
    	   		<ul class="nav navbar-nav navbar-right">
    	   			<li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
          			<li class="active"><a href="Cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
          			<%
          				Integer x = (Integer)session.getAttribute("u_id");
          				if(session.getAttribute("u_id") == null){
          			%>
          			<li id="loginbutton"><a href="login.jsp"><span class="glyphicon glyphicon glyphicon-user"></span> login</a></li>
          			<li id="registerbutton"><a href="register.jsp"><span class="glyphicon glyphicon-list"></span> register</a></li>
    	   			<%
          				}
    	   				else{
    	   			%>
    	   			<li><a href="WishList.jsp"><span class="glyphicon glyphicon-heart"></span> wishlist</a></li>
    	   			<li><a href="UserController?action=logout"><span class="glyphicon glyphicon-off"></span> logout</a></li>
    	   			<%
          				}
    	   			%>
    	   		</ul>
            </div>		
   		</div>
   </nav>
		<h1>Shopping Cart</h1>
		<%
		Integer u_id = (Integer)session.getAttribute ("u_id");
		ArrayList<Product> cart;
		if(u_id!=null) {
			cart = (ArrayList<Product>) session.getAttribute ("cart"+u_id);
		}
		else {
			cart = (ArrayList<Product>) session.getAttribute ("cart");
		}
		if (cart !=null) {
		%>
		
		<table id="cart_tab" class="table table-bordered table-striped table-hover">
		<tr><th>product name</th><th>price</th><th>description</th><th>Remove from Cart</th></tr>
		<%	
			String tr = "";
			for(Product p : cart) {
				tr+="<tr id="+p.getId()+">"+"<td>"+p.getName()+"</td>"
						+"<td>$"+p.getPrice()+"</td>"+"<td>"+p.getDecription()+"</td>"
						+"<td class='removefromcart'>"+"<a ><img src='img/delete_icon.png' style='width:24px;height:24px' /></a>"+"</td>"
						+"</tr>";
			}
			tr+="</table>";
			out.print(tr);	
		%>
	</table>
		<%
		}
		else {
			out.print("<p>Cart is empty</p>");
		}
		String name = (String) session.getAttribute("remove_from_cart_success");
		if(name!=null) {
			out.print("<p>"+name+"</p>");
			session.setAttribute("remove_from_cart_success",null);
		}
		%>
</body>
</html>