<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Account"%>
 <%
	if(session.getAttribute("username")==null){
		response.sendRedirect("loginAdmin");
	}
 	Account auth = (Account) request.getSession().getAttribute("username");
 	if (auth != null) {
    request.setAttribute("person", auth);
 }
 %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	      <!-- ========== header start ========== -->
      <header class="header">
        <div class="container-fluid">
          <div class="row">
            <div class="col-lg-5 col-md-5 col-6">
              <div class="header-left d-flex align-items-center">
                <div class="menu-toggle-btn mr-20">
                  <button
                    id="menu-toggle"
                    class="main-btn primary-btn btn-hover"
                  >
                    <i class="lni lni-chevron-left me-2"></i> Menu
                  </button>
                </div>
                <div class="header-search d-none d-md-flex">
                  <form action="#">
                    <input type="text" placeholder="Search..." />
                    <button><i class="lni lni-search-alt"></i></button>
                  </form>
                </div>
              </div>
            </div>
            <div class="col-lg-7 col-md-7 col-6">
              <div class="header-right">
                <!-- notification start -->
                <div class="notification-box ml-15 d-none d-md-flex">
                  <button
                    class="dropdown-toggle"
                    type="button"
                    id="notification"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <i class="lni lni-alarm"></i>
                    <span>2</span>
                  </button>
                  <ul
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="notification"
                  >
                    <li>
                      <a href="#0">
                        <div class="image">
                          <img src="${url}/assets/images/lead/lead-6.png" alt="" />
                        </div>
                        <div class="content">
                          <h6>
                            Kim Anh
                            <span class="text-regular">
                              comment on a product.
                            </span>
                          </h6>
                          <p>
                            Sản phẩm đẹp chất lượng tốt.
                          </p>
                          <span>10 mins ago</span>
                        </div>
                      </a>
                    </li>
                    <li>
                      <a href="#0">
                        <div class="image">
                          <img src="${url}/assets/images/lead/lead-1.png" alt="" />
                        </div>
                        <div class="content">
                          <h6>
                            Lê Vân
                            <span class="text-regular">
                              like on a product.
                            </span>
                          </h6>
                          <p>
                            Cửa hàng nhiều mẫu mã đẹp, rất ưng ý.
                          </p>
                          <span>10 mins ago</span>
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
                <!-- notification end -->
                <!-- message start -->
                <div class="header-message-box ml-15 d-none d-md-flex">
                  <button
                    class="dropdown-toggle"
                    type="button"
                    id="message"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <i class="lni lni-envelope"></i>
                    <span>3</span>
                  </button>
                  <ul
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="message"
                  >
                    <li>
                      <a href="#0">
                        <div class="image">
                          <img src="${url}/assets/images/lead/lead-5.png" alt="" />
                        </div>
                        <div class="content">
                          <h6>Nguyễn Oanh</h6>
                          <p>Bên store quần jean hàng đã về chưa ...</p>
                          <span>10 mins ago</span>
                        </div>
                      </a>
                    </li>
                    <li>
                      <a href="#0">
                        <div class="image">
                          <img src="${url}/assets/images/lead/lead-3.png" alt="" />
                        </div>
                        <div class="content">
                          <h6>Nguyễn Yến</h6>
                          <p>Store có mẫu nào mới về không ạ.</p>
                          <span>12 mins ago</span>
                        </div>
                      </a>
                    </li>
                    <li>
                      <a href="#0">
                        <div class="image">
                          <img src="${url}/assets/images/lead/lead-2.png" alt="" />
                        </div>
                        <div class="content">
                          <h6>Quỳnh Nga</h6>
                          <p>Chào shop! mình có thể đặt hàng online được không.</p>
                          <span>1h ago</span>
                        </div>
                      </a>
                    </li>
                  </ul>
                </div>
                <!-- message end -->
                <!-- filter start -->
                <div class="filter-box ml-15 d-none d-md-flex">
                  <button class="" type="button" id="filter">
                    <i class="lni lni-funnel"></i>
                  </button>
                </div>
                <!-- filter end -->
                <!-- profile start -->
                <div class="profile-box ml-15">
                  <button
                    class="dropdown-toggle bg-transparent border-0"
                    type="button"
                    id="profile"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <div class="profile-info">
                      <div class="info">                     
                        <h6><%=auth.getFullName() %></h6> 
                        <div class="image">
                          <img
                            src="${url}/assets/images/profile/profile-image.png"
                            alt=""
                          />
                         
                          <span class="status"></span>
                        </div>
                      </div>
                    </div>
                    <i class="lni lni-chevron-down"></i>
                  </button>
                  <ul
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="profile"
                  >
                    <li>
                      <a href="#0">
                        <i class="lni lni-user"></i> <span>My Profile</span>
                      </a>
                    </li>
                    <li>
                      <a href="#0">
                        <i class="lni lni-alarm"></i> <span>Notifications</span></a>
                    </li>
                    <li>
                      <a href="#0"> <i class="lni lni-inbox"></i> <span>Messages</span> </a>
                    </li>
                    <li>
                      <a href="#0"> <i class="lni lni-cog"></i> <span>Setting</span> </a>
                    </li>
                    <li>
                      <a href="logoutAdmin"> <i class="lni lni-exit"></i> <span>Sign Out</span> </a>
                    </li>
                  </ul>
                </div>
                <!-- profile end -->
              </div>
            </div>
          </div>
        </div>
      </header>
</body>
</html>