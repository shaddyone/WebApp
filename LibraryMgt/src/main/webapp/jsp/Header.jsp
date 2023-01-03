

<%@page import="in.co.lib.mgt.controller.LoginCtl"%>
<%@page import="in.co.lib.mgt.controller.LIBView"%>
<%@page import="in.co.lib.mgt.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

 <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="/LibraryMgt/css/bootstrap.css">
        <link rel="stylesheet" href="/LibraryMgt/css/home.css">
        <link rel="stylesheet" href="/LibraryMgt/vendors/linericon/style.css">
        <link rel="stylesheet" href="/LibraryMgt/css/font-awesome.min.css">
        <link rel="stylesheet" href="/LibraryMgt/vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="/LibraryMgt/vendors/lightbox/simpleLightbox.css">
        <link rel="stylesheet" href="/LibraryMgt/vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="/LibraryMgt/vendors/animate-css/animate.css">
        <link rel="stylesheet" href="/LibraryMgt/vendors/popup/magnific-popup.css">
        <!-- main css -->
        <link rel="stylesheet" href="/LibraryMgt/css/style.css">
        <link rel="stylesheet" href="/LibraryMgt/css/responsive.css">
        <link rel="stylesheet" href="/LibraryMgt/css/login.css">
        
  
</head>
<body>

<%
	UserBean userDto = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userDto != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		welcomeMsg += userDto.getFirstName().toUpperCase();
	} else {
		welcomeMsg += "Guest";
	}
%>
       <header class="header_area">
          	
            <div class="main_menu">
            	<nav class="navbar navbar-expand-lg navbar-light">
					<div class="container">
						<!-- Brand and toggle get grouped for better mobile display -->
						<a class="navbar-brand logo_h" href="index.html">Library Management</a>
						
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
							<ul class="nav navbar-nav menu_nav ml-auto">
								<li class="nav-item active"><a class="nav-link" href="<%=LIBView.WELCOME_CTL%>">Home</a></li>
				<%
				if (userLoggedIn) {
				%> 
				
				<%if(userDto.getRoleId()==1){ %>
								<li class="nav-item"><a class="nav-link" href="<%=LIBView.USER_LIST_CTL%>">User</a></li>
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Book Dashbord</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=LIBView.BOOK_CTL%>">Add Book</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=LIBView.BOOK_LIST_CTL%>">Books</a></li>
									</ul>
								</li> 
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Issue Book</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=LIBView.ISSU_BOOK_CTL%>">Issue Book </a></li>
										<li class="nav-item"><a class="nav-link" href="<%=LIBView.ISSU_BOOK_LIST_CTL%>">Issue Book Report</a></li>
										
									</ul>
								</li> 
								<li class="nav-item"><a class="nav-link" href="<%=LIBView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
				
				<%}
				if(userDto.getRoleId()==2){%>
				
						<li class="nav-item"><a class="nav-link" href="<%=LIBView.USER_CTL%>">My Profile</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=LIBView.ISSU_BOOK_LIST_CTL%>">Issue Book</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=LIBView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
						
					<%}} %>	
						<%
 					if (userLoggedIn) {
						 %>
				 
							
								<li class="nav-item"><a class="nav-link" href="<%=LIBView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a></li>
								
					<%}else{ %>			
								<li class="nav-item"><a class="nav-link" href="<%=LIBView.LOGIN_CTL%>">Login</a></li> 
								<li class="nav-item"><a class="nav-link" href="<%=LIBView.USER_REGISTRATION_CTL%>">SignUp</a></li>
								
					<%} %>		
							</ul>
						</div> 
					</div>
            	</nav>
            </div>
        </header>
</body>
</html>