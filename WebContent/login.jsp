<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.controller.UserController" %>
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
<title>Login</title>
</head>
<body>
	<nav class="navbar navbar-default">
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
          			<li><a href="Cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
          			<%
          				Integer x = (Integer)session.getAttribute("u_id");
          				if(session.getAttribute("u_id") == null){
          			%>
          			<li class="active" id="loginbutton"><a href="login.jsp"><span class="glyphicon glyphicon glyphicon-user"></span> login</a></li>
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
	<form method="post" action="UserController">
	<table class="form-group">
		<tr>
			<td>Username </td>
			<td><input style="margin:10px;" class="form-control" type="text" name="username" / ></td>
		</tr>
		<tr>
			<td>Password </td>
			<td><input style="margin:10px;" class="form-control" type="password" name="password" / ></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type="submit" name="login" value="login" /></td>
		</tr>
	</table>
	</form>
	<%
			String name = (String) session.getAttribute("not_login");
			if(name!=null) {
				out.print("<p>"+name+"</p>");
				session.setAttribute("not_login",null);
			}
	%>
</body>
</html>