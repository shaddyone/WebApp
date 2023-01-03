
<%@page import="in.co.lib.mgt.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.lib.mgt.controller.UserRegistrationCtl"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<link rel="stylesheet" href="<%=LIBView.APP_CONTEXT%>/css/jquery-ui.css">

<script src="<%=LIBView.APP_CONTEXT%>/js/jquery-1.12.4.js"></script>
<script src="<%=LIBView.APP_CONTEXT%>/js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'mm/dd/yy',
			
			changeMonth : true,
			changeYear : true,
			
		});
	});
</script>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 19px ;margin-top: 68px" >
<h2 style="color: white; font-family:inherit;">User Regisration</h2></div>
<br>
<div class="container">
    <h1 class="well">Registration Form</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=LIBView.USER_REGISTRATION_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.co.lib.mgt.bean.UserBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>First Name</label>
								<input type="text" name="firstName" placeholder="Enter First Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getFirstName())%>" >
								<b><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group">
								<label>Last Name</label>
								<input type="text" name="lastName" placeholder="Enter Last Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getLastName())%>">
								<b><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></b>
							</div>
						</div>					
							
					<div class="form-group">
						<label>Login Id</label>
						<input type="text" name="login" placeholder="Enter Login Address Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getLogin())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></b>
					</div>
						
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Password</label>
								<input type="password" name="password" placeholder="Enter password Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getPassword())%>">
								<b><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group">
								<label>Confirm Password</label>
								<input type="password" name="confirmPassword" placeholder="Enter Confirm Password Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
								<b><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></b>
							</div>
						</div>	
						
					<div class="form-group">
						<label>Date Of Birth</label>
						<input type="text" name="dob" placeholder="Enter Date Of Birth Here.." class="form-control"  readonly="readonly" value="<%=DataUtility.getDateString(bean.getDob())%>" id="datepicker">
						<b><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></b>
					</div>
					<div class="form-group">
						<label>Email Id</label>
						<input type="text" name="email" placeholder="Enter Email Address Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getEmailId())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font></b>
					</div>
					<div class="form-group">
						<label>Gender</label>
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");
						%>
						<%=HTMLUtility.getList("gender",String.valueOf(bean.getGender()), map) %>
						<b><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font></b>
					</div>
					<div class="form-group">
						<label>Mobile No.</label>
						<input type="text" name="mobile" placeholder="Enter Mobile No. Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font></b>
					</div>
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>">					
					</div>
				</form> 
				</div>
	</div>
	</div>
	<br><br>
	<hr>
	<br><br>
<%@include file="Footer.jsp"%>

</body>
</html>