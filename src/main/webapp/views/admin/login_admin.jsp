<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="assets/images/favicon.svg"
	type="image/x-icon" />
<title>Đăng nhập</title>

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

<c:url var="url" value="/views/admin"></c:url>

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


	<!-- ========== section start ========== -->
	<section class="signin-section">
		<div class="container-fluid">
			<!-- ========== title-wrapper start ========== -->
			<div class="title-wrapper pt-30">
				<div class="row align-items-center">
					<div class="col-md-6">
						<div class="title mb-30">
							<h2>Clothing Store</h2>
						</div>
					</div>
				</div>
				<!-- end row -->
			</div>
			<!-- ========== title-wrapper end ========== -->

			<div class="row g-0 auth-row">
				<div class="col-lg-6">
					<div class="auth-cover-wrapper bg-primary-100">
						<div class="auth-cover">
							<div class="title text-center">
								<h1 class="text-primary mb-10">Chào mừng bạn đã quay trở
									lại.</h1>
								<p class="text-medium">Hãy đăng nhập để tiếp tục!</p>
							</div>
							<div class="cover-image">
								<img src="${url}/assets/images/auth/signin-image.svg" alt="" />
							</div>
							<div class="shape-image">
								<img src="${url}/assets/images/auth/shape.svg" alt="" />
							</div>
						</div>
					</div>
				</div>
				<!-- end col -->
				<div class="col-lg-6">
					<div class="signin-wrapper">
						<div class="form-wrapper">
							<h6 class="mb-15">Đăng Nhập</h6>
							<form action="loginAdmin" method="post">
								<div class="row">
									<div class="col-12">
										<div class="input-style-1">
											<label for="yourUsername">Tài khoản</label> <input
												type="text" name="username" id="yourUsername"
												placeholder="Hãy nhập tài khoản" required />
										</div>
									</div>
									<!-- end col -->
									<div class="col-12">
										<div class="input-style-1">
											<label for="yourpassword">Mật khẩu</label> <input
												type="password" name="password" id="yourpassword"
												placeholder="Hãy nhập mật khẩu" required />
										</div>
									</div>
									<!-- end col -->
									<div class="col-xxl-6 col-lg-12 col-md-6">
										<div class="form-check checkbox-style mb-30">
											<input class="form-check-input" type="checkbox" value=""
												id="checkbox-remember" /> <label
												class="form-check-label" for="checkbox-remember">
												Ghi nhớ</label>
										</div>
										<c:if test="${requestScope.error !=null }">
											<p class="text-danger">${requestScope.error}</p>
										</c:if>
									</div>
									<!-- end col -->
									<div class="col-xxl-6 col-lg-12 col-md-6">
										<div
											class="
                            text-start text-md-end text-lg-start text-xxl-end
                            mb-30
                          ">
											<a href="#0" class="hover-underline">Quên
												mật khẩu?</a>
										</div>
									</div>
									<!-- end col -->
									<div class="col-12">
										<div
											class="
                            button-group
                            d-flex
                            justify-content-center
                            flex-wrap
                          ">
											<button
												class="
                              main-btn
                              primary-btn
                              btn-hover
                              w-100
                              text-center
                            ">
												Đăng nhập</button>
										</div>
									</div>
								</div>
								<!-- end row -->
							</form>
							<div class="singin-option pt-40">
								<p class="text-sm text-medium text-center text-gray">Đăng
									nhập bằng</p>
								<div
									class="
                        button-group
                        pt-40
                        pb-40
                        d-flex
                        justify-content-center
                        flex-wrap
                      ">
									<button class="main-btn primary-btn-outline m-2">
										<i class="lni lni-facebook-fill mr-10"></i> Facebook
									</button>
									<button class="main-btn danger-btn-outline m-2">
										<i class="lni lni-google mr-10"></i> Google
									</button>
								</div>
								<p class="text-sm text-medium text-dark text-center">
									Bạn chưa có mật khẩu? <a href="signup.html">Tạo tài khoản</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</section>
	<!-- ========== section end ========== -->

	<!-- ========== footer start =========== -->
	<footer class="footer">
		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.2); text-color: #E0E0E0">
			© 2022 Copyright: <a class="text-white" href="#">ITPLUS.com</a>
		</div>
		<!-- Copyright -->
	</footer>
	<!-- ========== footer end =========== -->

</body>
</html>