<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
    .table-controls {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    .page-length {
        display: flex;
        align-items: center;
    }
    
    /* Thêm mã CSS khác tùy theo yêu cầu của bạn */
</style>

<meta charset="UTF-8">
<title>List Product</title>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<c:url var="url" value="/views/admin"></c:url>

<link rel="shortcut icon" href="${url}/assets/images/favicon.svg"
	type="image/x-icon" />



<!-- Include jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Include DataTables CSS and JavaScript -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>




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
<body></body>
	<!-- ======== sidebar-nav start =========== -->
	<%@include file="layout/sidebar.jsp"%>
	<!-- ======== sidebar-nav end =========== -->

	<!-- ======== main-wrapper start =========== -->
	<main class="main-wrapper">
		<!-- ========== header start ========== -->
		<%@include file="layout/header.jsp"%>
		<!-- ========== header end ========== -->


		<section class="section dashboard">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Danh sách loại sản phẩm</h5>
							<div class="table-controls">
								<a href="addProduct" class="btn btn-primary">Thêm mới</a>
								<div class="page-length">
									Xem <select id="pageLengthSelect"></select> mục
								</div>
							</div>
							<table class="table" id="myTable">
								<thead>
									<tr>
										<th data-orderable="true" data-searchable="true">Id Sản Phẩm</th>
										<th data-orderable="true" data-searchable="true">Id brand</th>
										<th data-orderable="true" data-searchable="true">Tên Sản Phẩm</th>
										<th data-orderable="true" data-searchable="true">Ghi Chú</th>
										<th data-orderable="true" data-searchable="true">Giá sau giảm</th>
										<th data-orderable="true" data-searchable="true">Giá Gốc</th>
										<th data-orderable="true" data-searchable="true">% giảm giá</th>
										<th data-orderable="true" data-searchable="true">Ảnh Sản Phẩm</th>
										<th data-orderable="true" data-searchable="true">Size</th>
										<th data-orderable="true" data-searchable="true">Ngày Tạo :</th>
										<th data-orderable="true" data-searchable="true">Chức
											năng</th>
										<!-- Add other columns -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pro" items="${requestScope.products}">
										<tr>
											<td>${pro.id}</td>
											<td>${pro.brandId}</td>
											<td>${pro.name}</td>
											<td>${pro.description}</td>
											<td>${pro.price}</td>
											<td>${pro.priceMore}</td>
											<td>${pro.promotion}</td>
											<td><img
												src="${pageContext.request.contextPath}/views/admin/${pro.imageUrl}"
												height="50" width="50"></td>
											<td>${pro.size1}</td>
											<td>${pro.createdAt}</td>
											<td><a href="updateProduct?product_id=${pro.id}">Sửa</a>
												<a href="deleteProduct?product_id=${pro.id}">Xóa</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>

		<script>
			$(document)
					.ready(
							function() {
								var table = $('#myTable')
										.DataTable(
												{
													paging : true,
													searching : true,
													info : true,
													pageLength : 10, // Số dòng trên mỗi trang
													language : {
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
													},
												// Các tùy chọn khác
												});

								$('#searchInput').on('keyup', function() {
									table.search(this.value).draw();
								});
							});
		</script>
		<!-- ========== section start ========== -->

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
	<!-- ========== script start =========== -->
		
	<%@include file="layout/script.jsp" %>
	<!-- ========== script end =========== -->
		</body>
</html>
	