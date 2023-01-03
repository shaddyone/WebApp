
<%@page import="in.co.lib.mgt.controller.ChangePasswordCtl"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 19px ;margin-top: 68px" >
<h2 style="color: white; font-family: inherit;">Change Password</h2></div>
<br>
<div class="container">
    <h1 class="well">Change Password</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br><br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=LIBView.CHANGE_PASSWORD_CTL%>"  method="post" >
				
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
									
					<div class="form-group">
						<label>Old Password</label>
						<input type="password" name="oldPassword" placeholder="Enter Old Password Here.." class="form-control"  value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
						<b><font color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font></b>
					</div>
						
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>New Password</label>
								<input type="password" name="newPassword" placeholder="Enter New Password Here.." class="form-control value="<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>">
								<b><font color="red"><%=ServletUtility.getErrorMessage("newPassword", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group">
								<label>Confirm Password</label>
								<input type="password" name="confirmPassword" placeholder="Enter Confirm Password Here.." class="form-control"  value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
								<b><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></b>
							</div>
						</div>	
						
					
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=ChangePasswordCtl.OP_SAVE%>">					
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