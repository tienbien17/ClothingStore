<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="dao.*"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
Order order = (Order) session.getAttribute("order");
ArrayList<OrderItem> order_list = (ArrayList<OrderItem>) session.getAttribute("orderItems");
order_list = (ArrayList<OrderItem>) session.getAttribute("orderItems");
List<OrderItem> orderItems = null;
double total = 0;
OrderDAO orderDAO = new OrderDAO();
if (order_list != null) {
	total = orderDAO.calculateTotalPrice(order_list) + order.getShippingCost();
}
request.setAttribute("total", total);
System.out.println("Debug: order_list size = " + order_list.size());
System.out.println("Debug: order shipping cost = " + order.getShippingCost());
System.out.println("Debug: calculated total = " + orderDAO.calculateTotalPrice(order_list));
total = orderDAO.calculateTotalPrice(order_list) + order.getShippingCost();
System.out.println("Debug: total = " + total);
request.setAttribute("total", total);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
	background: rgb(213, 217, 233);
	min-height: 100vh;
	vertical-align: middle;
	display: flex;
	font-family: Muli;
	font-size: 14px
}

.card {
	margin: auto;
	width: 320px;
	max-width: 600px;
	border-radius: 20px
}

.mt-50 {
	margin-top: 50px;
}

.mb-50 {
	margin-bottom: 50px;
}

@media ( max-width :767px) {
	.card {
		width: 80%
	}
}

@media ( height :1366px) {
	.card {
		width: 75%
	}
}

#orderno {
	padding: 1vh 2vh 0;
	font-size: smaller
}

.gap .col-2 {
	background-color: rgb(213, 217, 233);
	width: 1.2rem;
	padding: 1.2rem;
	margin-top: -2.5rem;
	border-radius: 1.2rem
}

.title {
	display: flex;
	text-align: center;
	font-size: 2rem;
	font-weight: bold;
	padding: 12%
}

.main {
	padding: 0 2rem
}

.main img {
	border-radius: 7px
}

.main p {
	margin-bottom: 0;
	font-size: 0.75rem
}

#sub-title p {
	margin: 1vh 0 2vh 0;
	font-size: 1rem
}

.row-main {
	padding: 1.5vh 0;
	align-items: center
}

hr {
	margin: 1rem -1vh;
	border-top: 1px solid rgb(214, 214, 214)
}

.total {
	font-size: 1rem
}

@media ( height : 1366px) {
	.main p {
		margin-bottom: 0;
		font-size: 1.2rem
	}
	.total {
		font-size: 1.5rem
	}
}

.btn {
	background-color: rgb(3, 122, 219);
	border-color: rgb(3, 122, 219);
	color: white;
	margin: 7vh 0;
	border-radius: 7px;
	width: 60%;
	font-size: 0.8rem;
	padding: 0.8rem;
	justify-content: center
}

.btn:focus {
	box-shadow: none;
	outline: none;
	box-shadow: none;
	color: white;
	-webkit-box-shadow: none;
	-webkit-user-select: none;
	transition: none
}

.btn:hover {
	color: white
}
</style>
</head>
<body>
	<div class="card mt-50 mb-50">
		<!-- Your HTML content here -->
		<div class="d-flex">
			<span class="text-muted" id="orderno">order #${order.orderId}</span>
		</div>
		<div class="gap">
			<!-- Your other content here -->
		</div>
		<div class="title mx-auto">Thank you for your order!</div>
		<div class="main">
			<span id="sub-title">
				<p>
					<b>Payment Summary</b>
				</p>
			</span>
			<c:forEach var="orderItem" items="${orderItems}">
				<div class="row row-main">
					<div class="col-3">
					 <img src=" ${pageContext.request.contextPath}/views/admin/${orderItem.imageUrl}" height="50" width="50"  alt="">
					 
					</div>
					<div class="col-6">
						<div class="row d-flex">
							<p>
								<b>${orderItem.name}</b>
							</p>
						</div>
						<div class="row d-flex">
							<p class="text-muted">${orderItem.description}</p>
						</div>
						<div class="row d-flex">
							<p class="text-muted">Quantity: ${orderItem.quantity}</p>
						</div>
					</div>
					<div class="col-3 d-flex justify-content-end">
						<p>
							<b>${orderItem.priceMore}</b>
						</p>
					</div>
				</div>
			</c:forEach>
			<div class="col-3 d-flex justify-content-end">
				<p>
					<b>Shipping cost:${order.shippingCost}</b>
				</p>
			</div>
			<hr>
			<div class="total">
				<div class="row">
					<div class="col">
						<b> Total:</b>
					</div>
					<div class="col d-flex justify-content-end">
						<b>${dcf.format(total)}$</b>
					</div>
				</div>
			</div>
		</div>
</body>
</html>