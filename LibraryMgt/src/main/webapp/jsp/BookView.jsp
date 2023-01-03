<%@page import="in.co.lib.mgt.controller.BookCtl"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 19px ;margin-top: 68px" >
<h2 style="color: white; font-family:inherit;">Book Management</h2></div>
<br>
<div class="container">
    <h1 class="well">Add Book</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=LIBView.BOOK_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.co.lib.mgt.bean.BookBean"
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
						<label>Book Name</label>
						<input type="text" name="bookName" placeholder="Enter Book Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getBookName())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("bookName", request)%></font></b>
					</div>
						
					<div class="form-group">
						<label>Author Name</label>
						<input type="text" name="author" placeholder="Enter Author Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getWriterName())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("author", request)%></font></b>
					</div>
				
					
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=BookCtl.OP_SAVE%>">					
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