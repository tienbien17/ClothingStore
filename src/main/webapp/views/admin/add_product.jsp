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
							<form action="addProduct" method="post"
								enctype="multipart/form-data">
								<h3>Thêm mới sản phẩm</h3>

								<div class="mt-3">
									<label>Mã brand</label> <input type="number" name="brand_id"
										class="form-control" />
								</div>
								<div class="mt-3">
									<label>Danh Mục</label> <select name="category"
										class="form-control">
										<c:forEach items="${categories}" var="category">
											<option value="${category.categoryId}">${category.categoryName}</option>
										</c:forEach>
									</select>
									<!-- Thêm nút để tạo danh mục mới -->
								</div>
								<div class="mt-3">
									<label>Tên sản phẩm</label> <input type="text"
										name="product_name" class="form-control" />
								</div>
								<div class="mt-3">
									<label>Mô tả</label> <input type="text" name="description"
										class="form-control" />
								</div>
								<div class="mt-3">
									<label>Giá</label> <input type="number" name="price"
										class="form-control" />
								</div>
								<div class="mt-3">
									<label>Giá 2</label> <input type="number" name="price_more"
										class="form-control" />
								</div>

								<div class="mt-3">
									<label>Hình ảnh</label> <input type="file" name="file"
										class="form-control" />

								</div>
								<div class="mt-3">
									<label>size</label> <input type="number" name="size"
										class="form-control" />
								</div>
								<div class="mt-3">
									<label>Mã màu</label> <input type="number" name="color"
										class="form-control" />
								</div>

								<div class="mt-3">
									<button type="submit" class="btn btn-primary">Thêm mới</button>
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