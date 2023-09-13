<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới</title>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<c:url var="url" value="/views/admin"></c:url>

<link rel="shortcut icon" href="${url}/assets/images/favicon.svg"
	type="image/x-icon" />
<title>Danh sách sản phẩm</title>

<!-- ========== All CSS files linkup ========= -->
<link rel="stylesheet" href="${url}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${url}/assets/css/lineicons.css" />
<link rel="stylesheet"
	href="${url}/assets/css/materialdesignicons.min.css" />
<link rel="stylesheet" href="${url}/assets/css/fullcalendar.css" />
<link rel="stylesheet" href="${url}/assets/css/fullcalendar.css" />
<link rel="stylesheet" href="${url}/assets/css/main.css" />
</head>
<body>
	<!-- ======== sidebar-nav start =========== -->
	<%@include file="layout/sidebar.jsp"%>
	<!-- ======== sidebar-nav end =========== -->

	<!-- ======== main-wrapper start =========== -->
	<main class="main-wrapper">
		<!-- ========== header start ========== -->
		<%@include file="layout/header.jsp"%>
		<!-- ========== header end ========== -->

		<!-- ========== section start ========== -->
		<section class="section dashboard">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<form action="updateProduct" method="post">
								<h3>Cập nhật sản phẩm</h3>
								<c:if test="${requestScope.product != null }">
									<c:set var="cate" value="${resquestScope.product }"></c:set>

								</c:if>
								<div class="mt-3">
									<label>Mã Sản Phẩm</label> <input type="number" readonly="readonly" name="product_id"
										value="${product.id}" class="form-control" />
								</div>
								<div class="mt-3">
									<label>Mã brand</label> <input type="number" name="brand_id"
										value="${product.brandId}" class="form-control" />
								</div>
								<div class="mt-3">
									<label>Tên sản phẩm</label> <input type="text"
										name="product_name" value="${product.name}"
										class="form-control" />
								</div>
								<div class="mt-3">
									<label>Mô tả</label> <input type="text" name="description"
										value="${product.description}" class="form-control" />
								</div>
								<div class="mt-3">
									<label>price</label> <input type="number" name="price"
										value="${product.price}" class="form-control" />
								</div>
								<div class="mt-3">
									<label>price_more</label> <input type="number"
										name="price_more" value="${product.priceMore}"
										class="form-control" />
								</div>
								<div class="mt-3">
									<label>promotion</label> <input type="number" name="promotion"
										value="${product.promotion}" class="form-control" />
								</div>

								<div class="mt-3">
									<label>Hình ảnh</label> <input type="file" name="file"
										value="${product.imageUrl}" class="form-control" />

								</div>
								<div class="mt-3">
									<label>size</label> <input type="number" name="size"
										value="${product.size}" class="form-control" />
								</div>


								<div class="mt-3">
									<button type="submit" class="btn btn-primary">Cập nhật</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- ========== section end ========== -->

		<!-- ========== footer start =========== -->
		<%@include file="layout/footer.jsp"%>
		<!-- ========== footer end =========== -->
	</main>
	<!-- ======== main-wrapper end =========== -->

	<!-- ========= All Javascript files linkup ======== -->
	<script src="${url}/assets/js/bootstrap.bundle.min.js"></script>
	<script src="${url}/assets/js/Chart.min.js"></script>
	<script src="${url}/assets/js/dynamic-pie-chart.js"></script>
	<script src="${url}/assets/js/moment.min.js"></script>
	<script src="${url}/assets/js/fullcalendar.js"></script>
	<script src="${url}/assets/js/jvectormap.min.js"></script>
	<script src="${url}/assets/js/world-merc.js"></script>
	<script src="${url}/assets/js/polyfill.js"></script>
	<script src="${url}/assets/js/main.js"></script>

	<!-- ========== script start =========== -->
	<%@include file="layout/script.jsp"%>
	<!-- ========== script end =========== -->
</body>
</html>