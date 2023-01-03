<%@page import="in.co.lib.mgt.controller.IssuBookCtl"%>
<%@page import="in.co.lib.mgt.controller.UserRegistrationCtl"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicke" ).datepicker({
    	dateFormat : 'mm/dd/yy',
      changeMonth: true,
      changeYear: true
    });
  } );
  $( function() {
	    $( "#datepick" ).datepicker({
	    	dateFormat : 'mm/dd/yy',
	      changeMonth: true,
	      changeYear: true
	    });
	  } );
  </script>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 19px ;margin-top: 68px" >
<h2 style="color: white; font-family:inherit;">Issue Book</h2></div>
<br>
<div class="container">
    <h1 class="well">Issue Book</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=LIBView.ISSU_BOOK_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.co.lib.mgt.bean.IssuBookBean"
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
								<label>Book Code</label>
								<input type="text" name="bookCode" placeholder="Enter Book Code Here.."  class="form-control" value="<%=DataUtility.getStringData(bean.getBookCode())%>" >
								<b><font color="red"> <%=ServletUtility.getErrorMessage("bookCode", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group">
								<label>Student Code</label>
								<input type="text" name="studentCode" placeholder="Enter studentCode Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getStudentLibCode())%>">
								<b><font color="red"> <%=ServletUtility.getErrorMessage("studentCode", request)%></font></b>
							</div>
						</div>					
							
					
						
					<div class="form-group">
						<label>Issue Date</label>
						<input type="text" name="issuDate" placeholder="Enter Issue Date Here.."  readonly="readonly" class="form-control" value="<%=DataUtility.getDateString(bean.getIssuDate())%>" id="datepick">
						<b><font color="red"> <%=ServletUtility.getErrorMessage("issuDate", request)%></font></b>
					</div>
					
					<div class="form-group">
						<label>Return Date</label>
						<input type="text" name="returnDate" placeholder="Enter Return Date Here.."  readonly="readonly" class="form-control" value="<%=DataUtility.getDateString(bean.getReturnDate())%>" id="datepicke">
						<b><font color="red"> <%=ServletUtility.getErrorMessage("returnDate", request)%></font></b>
					</div>
					
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=IssuBookCtl.OP_SAVE%>">					
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