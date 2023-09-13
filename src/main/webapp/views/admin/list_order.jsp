<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Order</title>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<c:url var="url" value="/views/admin"></c:url>

<link rel="shortcut icon" href="${url}/assets/images/favicon.svg"
	type="image/x-icon" />



<title>Danh sách Order</title>

<!-- ========== All CSS files linkup ========= -->
<link rel="stylesheet" href="${url}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${url}/assets/css/lineicons.css" />
<link rel="stylesheet"
	href="${url}/assets/css/materialdesignicons.min.css" />
<link rel="stylesheet" href="${url}/assets/css/fullcalendar.css" />
<link rel="stylesheet" href="${url}/assets/css/fullcalendar.css" />
<link rel="stylesheet" href="${url}/assets/css/main.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.css" />
<script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#myTable')
								.DataTable(
										{
											"pageLength" : 10,
											"language" : {
												"sProcessing" : "Đang xử lý...",
												"sLengthMenu" : "Xem _MENU_ mục",
												"sZeroRecords" : "Không tìm thấy dòng nào phù hợp",
												"sInfo" : "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
												"sInfoEmpty" : "Đang xem 0 đến 0 trong tổng số 0 mục",
												"sInfoFiltered" : "(được lọc từ _MAX_ mục)",
												"sInfoPostFix" : "",
												"sSearch" : "Tìm:",
												"sUrl" : "",
												"oPaginate" : {
													"sFirst" : "Đầu",
													"sPrevious" : "Trước",
													"sNext" : "Tiếp",
													"sLast" : "Cuối"
												}
											}
										});
					});
</script>



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
							<h5 class="card-title">Danh sách list Order</h5>

							<table class="table" id="myTable">
								<thead>
									<tr>
										<th>orderId</th>
										<th>userId</th>
										<th>Ngày Tạo</th>
										<th>Phí Giao Hàng</th>
										<th>Dịa Chỉ</th>
										<th>Số Điện Thoại</th>
										<th>Ghi Chú</th>
										<th>Trạng Thái</th>
										<th>Sửa Trạng Thái</th>
										<th>Xem Order</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="order" items="${requestScope.orderList }">
										<tr>
											<td>${order.orderId}</td>
											<td>${order.userId}</td>
											<td>${order.orderDate}</td>
											<td>${order.shippingCost}</td>
											<td>${order.address}</td>
											<td>${order.phoneNumber}</td>
											<td>${order.description}</td>
											<td>${order.orderStatus}</td>
											<td><a href="updateorderstatus?id=${order.orderId}"
											 data-toggle="tooltip">Sửa</a></td>
											<td><a href="order-details?id=${order.orderId}"
												class="view" title="View Details" data-toggle="tooltip"><i
													class="material-icons">&#xE5C8;</i></a></td>
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
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
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>


	<!-- ========== script start =========== -->
	<%@include file="layout/script.jsp"%>
	<!-- ========== script end =========== -->
</body>
</html>