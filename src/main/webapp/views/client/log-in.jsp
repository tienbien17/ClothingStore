<%@page import="model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("carts");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<style>
		.container{
			margin-top: 200px;
		}
		.card-header{
			text-transform: uppercase;
			background-color: gray;
			color: white;
		}
		.text-center button{
			margin-top: 20px;
			text-align: center;
			text-transform: uppercase;
			color: white;
			background-color: blue;
		}
		.form-group label{
			margin: 5px;
		}
	</style>
</head>
<body background="https://assets.vogue.com/photos/6324cbb0563d9de75791b508/master/w_2560%2Cc_limit/___collage_story.jpg">
    <div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="log-in" method="post">
					<div class="form-group">
						<label>UserName</label> 
						<input type="username" name="username" class="form-control" placeholder="Enter email">
					</div>
					<div class="form-group">
						<label>Password</label> 
						<input type="password" name="password" class="form-control" placeholder="Password">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
					</div>
                         <c:if test="${requestScope.error !=null }">
	 								<p class="text-danger">${requestScope.error}</p>
					    </c:if>
                      </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>