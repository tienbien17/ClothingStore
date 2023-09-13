<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("carts");
List<Cart> cartProduct = null;
double total=0;
if (cart_list != null) {
	ProductDAO productDAO = new ProductDAO();
	 total = productDAO.getTotalPrice(cart_list);
	cartProduct = productDAO.getCartProducts(cart_list);
	request.setAttribute("cart_list", cart_list);
}
request.setAttribute("total", total);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giỏ hàng</title>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
@media ( min-width : 1025px) {
	.h-custom {
		height: 100vh !important;
	}
}

.number-input input[type="number"] {
	-webkit-appearance: textfield;
	-moz-appearance: textfield;
	appearance: textfield;
}

.number-input input[type=number]::-webkit-inner-spin-button,
	.number-input input[type=number]::-webkit-outer-spin-button {
	-webkit-appearance: none;
}

.number-input button {
	-webkit-appearance: none;
	background-color: transparent;
	border: none;
	align-items: center;
	justify-content: center;
	cursor: pointer;
	margin: 0;
	position: relative;
}

.number-input button:before, .number-input button:after {
	display: inline-block;
	position: absolute;
	content: '';
	height: 2px;
	transform: translate(-50%, -50%);
}

.number-input button.plus:after {
	transform: translate(-50%, -50%) rotate(90deg);
}

.number-input input[type=number] {
	text-align: center;
}

.number-input.number-input {
	border: 1px solid #ced4da;
	width: 10rem;
	border-radius: .25rem;
}

.number-input.number-input button {
	width: 2.6rem;
	height: .7rem;
}

.number-input.number-input button.minus {
	padding-left: 10px;
}

.number-input.number-input button:before, .number-input.number-input button:after
	{
	width: .7rem;
	background-color: #495057;
}

.number-input.number-input input[type=number] {
	max-width: 4rem;
	padding: .5rem;
	border: 1px solid #ced4da;
	border-width: 0 1px;
	font-size: 1rem;
	height: 2rem;
	color: #495057;
}

@media not all and (min-resolution:.001dpcm) { @
	supports (-webkit-appearance: none) and (stroke-color:transparent) { .number-input
		.def-number-input.safari_only button:before, .number-input.def-number-input.safari_onlybutton
		:after {
margin-top : -.3rem;
		
	}
}

}
.shopping-cart .def-number-input.number-input {
	border: none;
}

.shopping-cart .def-number-input.number-input input[type=number] {
	max-width: 2rem;
	border: none;
}

.shopping-cart .def-number-input.number-input input[type=number].black-text,
	.shopping-cart .def-number-input.number-input input.btn.btn-link[type=number],
	.shopping-cart .def-number-input.number-input input.md-toast-close-button[type=number]:hover,
	.shopping-cart .def-number-input.number-input input.md-toast-close-button[type=number]:focus
	{
	color: #212529 !important;
}

.shopping-cart .def-number-input.number-input button {
	width: 1rem;
}

.shopping-cart .def-number-input.number-input button:before,
	.shopping-cart .def-number-input.number-input button:after {
	width: .5rem;
}

.shopping-cart .def-number-input.number-input button.minus:before,
	.shopping-cart .def-number-input.number-input button.minus:after {
	background-color: #9e9e9e;
}

.shopping-cart .def-number-input.number-input button.plus:before,
	.shopping-cart .def-number-input.number-input button.plus:after {
	background-color: #4285f4;
}
</style>

</head>
<body>

	<section class="h-100 h-custom" style="background-color: #eee;">
		<div class="container h-100 py-5">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col">
					<div class="card shopping-cart" style="border-radius: 15px;">
						<div class="card-body text-black">

							<div class="row">
								<div class="col-lg-6 px-5 py-4">

									<h3 class="mb-5 pt-2 text-center fw-bold text-uppercase">Sản
										phẩm</h3>

									<%
									if (cart_list != null) {
										for (Cart c : cartProduct) {
									%>
									<div class="d-flex align-items-center mb-5">
										<div class="flex-shrink-0">
											<img
												src="${pageContext.request.contextPath}/views/admin/<%=c.getImageUrl()%>"
												class="img-fluid" style="width: 150px;"
												alt="Generic placeholder image">
										</div>
										<div class="flex-grow-1 ms-3">
											<a href="#!" class="float-end text-black"><i
												class="fas fa-times"></i></a>
											<h5 class="text-primary"><%=c.getName()%></h5>
											<h6 style="color: #9e9e9e;">Color: <%=c.getColors().iterator().next() %></h6>
											<h6 style="color: #9e9e9e;">Size: <%=c.getSize().iterator().next() %></h6>
											<div class="d-flex align-items-center">
												<p class="fw-bold mb-0 me-5 pe-3"><%=dcf.format(c.getPrice())%></p>
												<div class="def-number-input number-input safari_only">
													<a style="text-decoration: none;"
														href="quantity_controller?action=dec&id=<%=c.getId()%>"
														onclick=class="minus">-</a>
														 <input
														class="quantity fw-bold text-black" min="0"
														name="quantity" value=<%=c.getQuantity()%> type="number"  readonly="readonly">
													<a style="text-decoration: none;"
														href="quantity_controller?action=inc&id=<%=c.getId()%>
													"
														onclick=class="plus">+</a>
													<a href="remove-from-cart?id=<%=c.getId()%>"
														class="btn btn-sm btn-danger ms-auto">Xóa</a>
												</div>
											</div>
										</div>
									</div>
									<%
									}
									}
									%>
									<hr class="mb-4"
										style="height: 2px; background-color: #1266f1; opacity: 1;">

									<div class="d-flex justify-content-between px-x">
										<p class="fw-bold">Discount:</p>
										<p class="fw-bold">95$</p>
									</div>

									<div class="d-flex justify-content-between p-2 mb-2"
										style="background-color: #e1f5fe;">
										<h5 class="fw-bold mb-0">Total:</h5>
										<h5 class="fw-bold mb-0">${dcf.format(total)}$</h5>
									</div>

								</div>
								<div class="col-lg-6 px-5 py-4">

									<h3 class="mb-5 pt-2 text-center fw-bold text-uppercase">Thông
										tin khách hàng</h3>

									<form class="mb-5" action="check-out" method="post">

										<div class="form-outline mb-5">
											<label class="form-label" for="typeText">Họ và tên</label> <input name = "name"
												type="text" class="form-control form-control-lg" size="17"
												placeholder="Nhập họ và tên của bạn" minlength="19"
												maxlength="19" />

										</div>

										<div class="form-outline mb-5">
											<label class="form-label" for="typeName">Số điện
												thoại</label> <input name="phoneNumber" type="text"
												class="form-control form-control-lg" size="17"
												placeholder="Nhập số điện thoại" />

										</div>
										<div class="form-outline mb-5">
											<label class="form-label" for="typeName">Địa chỉ nhận
												hàng</label> <input name = "address" type="text" class="form-control form-control-lg"
												size="17" placeholder="Nhập địa chỉ nhận hàng" />

										</div>
										<div class="form-outline mb-5">
											<label for="comment">Ghi chú</label>
											<textarea name="description" class="form-control" rows="4"
												id="comment" name="text"></textarea>

										</div>
										<div class="form-outline mb-5">

											<label> <input type="checkbox" name="checkOut ">
												Thanh toán khi nhận hàng
											</label>
										</div>
										<button type="submit" class="btn btn-primary btn-block btn-lg">MUA
											NGAY</button>
										<h5 class="fw-bold mb-5"
											style="position: absolute; bottom: 0;">
											<a href="index"><i class="fas fa-angle-left me-2"></i>Quay
												lại trang chủ</a>
										</h5>

									</form>

								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.2); text-color: #E0E0E0">
			© 2022 Copyright: <a class="text-white" href="#">ITPLUS.com</a>
		</div>
		<!-- Copyright -->
	</section>
</body>
</html>

