<%@page import="model.Account"%>
<%@page import="model.Cart"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
Account auth = (Account) request.getSession().getAttribute("username");
if (auth != null) {
	request.setAttribute("person", auth);
}

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
<title>Project ShopOnline</title>

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
.dropdown:hover .dropdown-menu {
	display: block;
}

.carousel-item {
	height: 50vh;
}

.card {
	box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}

.footer-cta {
	box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 15px;
}

.price {
	color: #263238;
	font-size: 24px;
}

.card-title {
	color: #263238
}

.sale {
	color: #E53935
}

.sale-badge {
	background-color: #E53935
}

.footer-links {
	display: block;
	text-decoration: none;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-white">
		<!-- Container wrapper -->
		<div class="container">
			<!-- Toggle button -->
			<button class="navbar-toggler" type="button"
				data-mdb-toggle="collapse"
				data-mdb-target="#navbarSupportedContent1"
				aria-controls="navbarSupportedContent1" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars"></i>
			</button>

			<!-- Collapsible wrapper -->
			<div class="collapse navbar-collapse" id="navbarSupportedContent1">
				<!-- Navbar brand -->
				<a class="navbar-brand mt-2 mt-sm-0" href="https://mdbootstrap.com/">
				</a>
				<div class="col-lg-2 col-sm-4 col-4">
					<a href="http://itplus-academy.edu.vn/" target="_blank"
						class="float-start"> <img
						src="http://itplus-academy.edu.vn/public/images/logo.png"
						height="35" />
					</a>
				</div>
				<!-- Left links -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item active"><a class="nav-link " href="index"><i
							class="fa-solid fa-house px-2"></i>Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Giới
							thiệu</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Liên lạc</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Khác</a></li>
				</ul>
				<!-- Left links -->
			</div>
			<!-- Collapsible wrapper -->

			<!-- Right elements -->
			<div class="d-flex align-items-center">
				<!-- Icon -->
				<a class="nav-link me-3" href="https://www.facebook.com/"> <i
					class="fab fa-facebook-f"></i>
				</a> <a class="nav-link me-3" href="#"> <i class="fab fa-twitter"></i>
				</a> <a class="nav-link me-3" href="cart"> <i
					class="fas fa-shopping-cart"></i> <span
					class="badge rounded-pill badge-notification bg-danger">${cart_list.size()}</span>
				</a>
				<%
				if (auth != null) {
				%>
				<li class="nav-item" style="list-style-type: none; margin: 5px"><a
					class="nav-link" href="orders">Orders</a></li>
				<li class="nav-item" style="list-style-type: none;"><a
					class="nav-link" href="log-out">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item" style="list-style-type: none; margin: 5px"><a
					class="nav-link" href="log-in">Login</a></li>

				<li class="nav-item" style="list-style-type: none;"><a
					class="nav-link" href="register">Register</a></li>
				<%
				}
				%>

			</div>
			<!-- Right elements -->

		</div>
		<!-- Container wrapper -->
	</nav>
	<!-- Navbar -->





	<!-- carousel -->
	<div id="carouselExampleCaptions" class="carousel slide carousel-fade"
		data-mdb-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-mdb-target="#carouselExampleCaptions"
				data-mdb-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-mdb-target="#carouselExampleCaptions"
				data-mdb-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-mdb-target="#carouselExampleCaptions"
				data-mdb-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img
					src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/8-col/img%282%29.jpg"
					class="d-block w-100" alt="Wild Landscape" />
				<div class="mask" style="background-color: rgba(0, 0, 0, 0.4)"></div>
				<div class="carousel-caption d-none d-sm-block mb-5">
					<h1 class="mb-4">
						<strong>ClothingShop luôn mang lại sự thoải mái!</strong>
					</h1>

					<p>
						<strong>Bán quần áo là bán cái đẹp nhưng người bán sẽ
							không bớt đẹp còn người mua thì đẹp hơn</strong>
					</p>

					<p class="mb-4 d-none d-md-block">
						<strong>Học cách yêu bản thân, đơn giản là mua đồ mới sau
							đó thưởng thức một hình ảnh mình thật xinh đẹp, thật ngầu. Tại
							sao không? Hãy để chúng mình tư vấn cho bạn nhé!.</strong>
					</p>

					<a target="_blank" href="#" class="btn btn-outline-white btn-lg">Start
						free tutorial <i class="fas fa-graduation-cap ms-2"></i>
					</a>
				</div>
			</div>
			<div class="carousel-item">
				<img
					src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/8-col/img%283%29.jpg"
					class="d-block w-100" alt="Camera" />
				<div class="mask" style="background-color: rgba(0, 0, 0, 0.4)"></div>
				<div class="carousel-caption d-none d-md-block mb-5">
					<h1 class="mb-4">
						<strong>Learn Bootstrap 5 with MDB</strong>
					</h1>

					<p>
						<strong>Best & free guide of responsive web design</strong>
					</p>

					<p class="mb-4 d-none d-md-block">
						<strong>The most comprehensive tutorial for the Bootstrap
							5. Loved by over 3 000 000 users. Video and written versions
							available. Create your own, stunning website.</strong>
					</p>

					<a target="_blank"
						href="https://mdbootstrap.com/education/bootstrap/"
						class="btn btn-outline-white btn-lg">Start free tutorial <i
						class="fas fa-graduation-cap ms-2"></i>
					</a>
				</div>
			</div>
			<div class="carousel-item">
				<img
					src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/8-col/img%285%29.jpg"
					class="d-block w-100" alt="Exotic Fruits" />
				<div class="mask" style="background-color: rgba(0, 0, 0, 0.4)"></div>
				<div class="carousel-caption d-none d-md-block mb-5">
					<h1 class="mb-4">
						<strong>Learn Bootstrap 5 with MDB</strong>
					</h1>

					<p>
						<strong>Best & free guide of responsive web design</strong>
					</p>

					<p class="mb-4 d-none d-md-block">
						<strong>The most comprehensive tutorial for the Bootstrap
							5. Loved by over 3 000 000 users. Video and written versions
							available. Create your own, stunning website.</strong>
					</p>

					<a target="_blank"
						href="https://mdbootstrap.com/education/bootstrap/"
						class="btn btn-outline-white btn-lg">Start free tutorial <i
						class="fas fa-graduation-cap ms-2"></i>
					</a>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-mdb-target="#carouselExampleCaptions" data-mdb-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-mdb-target="#carouselExampleCaptions" data-mdb-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<!--Main layout-->
	<main>

		<div class="container">
			<!-- Navbar -->
			<nav class="navbar navbar-expand-lg navbar-dark mt-3 mb-5 shadow p-2"
				style="background-color: #607D8B">
				<!-- Container wrapper -->
				<div class="container-fluid">

					<!-- Navbar brand -->
					<a class="navbar-brand" href="#">Thể loại:</a>

					<!-- Toggle button -->
					<button class="navbar-toggler" type="button"
						data-mdb-toggle="collapse"
						data-mdb-target="#navbarSupportedContent2"
						aria-controls="navbarSupportedContent2" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fas fa-bars"></i>
					</button>

					<!-- Collapsible wrapper -->
					<%
					List<Category> topCategories = new ArrayList<Category>();
					List<Category> subCategories = new ArrayList<Category>();

					for (Category category : (List<Category>) request.getAttribute("categories")) {
						if (category.getParentCategory() == null || category.getParentCategory().getCategoryId() == 0) {
							topCategories.add(category);
						} else {
							subCategories.add(category);
						}
					}
					%>

					<div class="collapse navbar-collapse" id="navbarSupportedContent2">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<!-- Loop through top-level categories (tầng 1) -->
							<c:forEach items="<%=topCategories%>" var="topCategory">
								<a href="category?id=${topCategory.categoryId}">
									<li class="nav-item dropdown"><a
										class="nav-link dropdown-toggle text-white"
										href="category?id=${topCategory.categoryId}" role="button"
										id="dropdown${topCategory.categoryId}"
										data-bs-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> ${topCategory.categoryName} </a>
										<div class="dropdown-menu"
											aria-labelledby="dropdown${topCategory.categoryId}">
											<!-- Loop through sub-categories (tầng 2) -->
											<c:forEach items="<%=subCategories%>" var="subCategory">
												<c:if
													test="${subCategory.parentCategory.categoryId == topCategory.categoryId}">
													<a class="dropdown-item"
														href="category?id=${subCategory.categoryId}">
														${subCategory.categoryName} </a>
												</c:if>
											</c:forEach>
										</div></li>
								</a>
							</c:forEach>
						</ul>

						<!-- Search -->
						<form class="form-inline my-2 my-lg-0" action="search"
							method="GET">
							<input type="search" class="form-control mr-sm-2 rounded-0"
								placeholder="Search" aria-label="Search" name="name">
							<!-- <button type="submit" class="btn btn-primary my-2 my-sm-0">Tìm
								kiếm</button>0 --> 
						</form>

					</div>

					<script>
						// Redirect when clicking on top-level category link
						var topLinks = document
								.querySelectorAll('.nav-item.dropdown a.nav-link');
						topLinks.forEach(function(link) {
							link.addEventListener('click', function(e) {
								var dropdown = this.closest('.dropdown');
								if (!dropdown.classList.contains('show')) {
									e.preventDefault();
									window.location.href = this
											.getAttribute('href');
								}
							});
						});
					</script>




				</div>
				<!-- Container wrapper -->
			</nav>
			<!-- Navbar -->

			<!-- Products -->
			<section>
				<div class="row"></div>
					<div class="text-center">
						<div class="row">

							<c:forEach items="${requestScope.products}" var="pd">
								<div class="col-lg-3 col-md-6 mb-4">
									<div class="card">
										<div
											class="bg-image hover-zoom ripple ripple-surface ripple-surface-light"
											data-mdb-ripple-color="light">
											<a href="product?id=${pd.id}"> 
											<img src="${pageContext.request.contextPath}/views/admin${pd.imageUrl}" class="w-100">
											</a> <a href="#!"></a> <a href="#!">
												<div class="mask">
													<div
														class="d-flex justify-content-start align-items-end h-100">
														<h5>
															<span class="badge bg-dark ms-2">NEW</span>
														</h5>
													</div>
												</div>
												<div class="hover-overlay">
													<div class="mask"
														style="background-color: rgba(251, 251, 251, 0.15);"></div>
												</div>

											</a>
										</div>
										<div class="card-body">
											<a href="product?id=${pd.id}" class="text-reset">
												<h5 class="card-title mb-2">${pd.name}</h5>
											</a> <a href="category?id=${pd.category.categoryId}"
												class="text-reset ">
												<p>${pd.category.categoryName}</p>
											</a>
											<h6 class="mb-3 price">${pd.price}</h6>
											<div>
												<a href="add-to-cart?id=${pd.id}" class="btn btn-dark">Thêm
													vào giỏ hàng</a> <a href="product?id=${pd.id}"
													class="btn btn-primary">Xem Sản Phẩm</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
			</section>



			<!-- Pagination -->
			<nav aria-label="Page navigation example"
				class="d-flex justify-content-center mt-3">
				<ul class="pagination">
					<li class="page-item disabled"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
			<!-- Pagination -->
		</div>
	</main>
	<!--Main layout-->

	<footer class="text-center text-white mt-4"
		style="background-color: #a5d3e3">

		<!--Call to action-->

		<div class="footer">
			<h3 class="footer-title">About us</h3>
			<ul class="footer-links">
				<li><a href="#"><i class="fa fa-map-marker"></i>Cầu giấy -
						Bắc Từ Liêm - Hà Nội</a></li>
				<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
				<li><a href="#"><i class="fa fa-envelope-o"></i>sakai@gmail.com</a></li>
			</ul>
		</div>

		<!--/.Call to action-->

		<hr class="text-dark">

		<div class="container">
			<!-- Section: Social media -->
			<section class="mb-3">

				<!-- Facebook -->
				<a class="btn-link btn-floating btn-lg text-white" href="#!"
					role="button" data-mdb-ripple-color="dark"><i
					class="fab fa-facebook-f"></i></a>

				<!-- Twitter -->
				<a class="btn-link btn-floating btn-lg text-white" href="#!"
					role="button" data-mdb-ripple-color="dark"><i
					class="fab fa-twitter"></i></a>

				<!-- Google -->
				<a class="btn-link btn-floating btn-lg text-white" href="#!"
					role="button" data-mdb-ripple-color="dark"><i
					class="fab fa-google"></i></a>

				<!-- Instagram -->
				<a class="btn-link btn-floating btn-lg text-white" href="#!"
					role="button" data-mdb-ripple-color="dark"><i
					class="fab fa-instagram"></i></a>

				<!-- YouTube -->
				<a class="btn-link btn-floating btn-lg text-white" href="#!"
					role="button" data-mdb-ripple-color="dark"><i
					class="fab fa-youtube"></i></a>
				<!-- Github -->
				<a class="btn-link btn-floating btn-lg text-white" href="#!"
					role="button" data-mdb-ripple-color="dark"><i
					class="fab fa-github"></i></a>
			</section>
			<!-- Section: Social media -->
		</div>
		<!-- Grid container -->

		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.2); text-color: #E0E0E0">
			© 2022 Copyright: <a class="text-white" href="#">ITPLUS.com</a>
		</div>
		<!-- Copyright -->
	</footer>
</body>
</html>

