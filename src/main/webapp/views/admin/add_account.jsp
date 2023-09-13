<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới Account</title>
 <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
        <c:url var="url" value="/views/admin"></c:url>
    
    <link
      rel="shortcut icon"
      href="${url}/assets/images/favicon.svg"
      type="image/x-icon"
    />
    <title>Danh sách Account</title>

    <!-- ========== All CSS files linkup ========= -->
    <link rel="stylesheet" href="${url}/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${url}/assets/css/lineicons.css" />
    <link rel="stylesheet" href="${url}/assets/css/materialdesignicons.min.css" />
    <link rel="stylesheet" href="${url}/assets/css/fullcalendar.css" />
    <link rel="stylesheet" href="${url}/assets/css/fullcalendar.css" />
    <link rel="stylesheet" href="${url}/assets/css/main.css" />
  </head>
  <body>
    <!-- ======== sidebar-nav start =========== -->
    <%@include file="layout/sidebar.jsp" %>
    <!-- ======== sidebar-nav end =========== -->

    <!-- ======== main-wrapper start =========== -->
    <main class="main-wrapper">
	 <!-- ========== header start ========== -->
	 <%@include file="layout/header.jsp" %>
      <!-- ========== header end ========== -->

      <!-- ========== section start ========== -->
    				<section class="section dashboard">
			<div class="row">				
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<form action="addAccount" method="post">
								<h3>Thêm mới Account</h3>
								<c:if test="${requestScope.error !=null }">
	 								<p class="text-danger">${requestScope.error}</p>
								</c:if>
									<div class="mt-3">
										<label>Full name</label>
										<input type="text" name="full_name" class="form-control"></input>
									</div>
									<div class="mt-3">
										<label>User name</label>
										<input type="text" name="username" class="form-control"></input>
									</div>
									<div class="mt-3">
										<label>Password</label>
										<input type="text" name="password" class="form-control"></input>
									</div>
									<div class="mt-3">
										<label>Address</label>
										<input type="text" name=address class="form-control"></input>
									</div>
									<div class="mt-3">
										<label>Phone number</label>
										<input type="text" name=phone_number class="form-control"></input>
									</div>
									<!-- <div class="mt-3">
										<label>created_at</label>
										<input type="date" name=created_at class="form-control"></input>
									</div> -->
									
									<div class="mt-3">
										<label>email</label>
										<input type="text" name=email class="form-control"></input>
									</div>
									<!--<div class="mt-3">
										<label>Active</label>
										<input type="checkbox" name="active" value="active" ></input>
									</div>  -->
																		
									<div class="mt-3">
										<label>User role</label>
										<input type="text" name="role_code" class="form-control"></input>
									</div>
									<div class="mt-3">
										<input type="submit" value="Thêm mới" class="btn btn-primary"></input>
									</div>
								</form>
							</div>
						</div>
					</div>			
			</div>
		</section>
      <!-- ========== section end ========== -->

      <!-- ========== footer start =========== -->
		<%@include file="layout/footer.jsp" %>
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
		<%@include file="layout/script.jsp" %>
      <!-- ========== script end =========== -->
  </body>
</html>