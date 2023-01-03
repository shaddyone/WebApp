<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript">
        $("#btnPrint").live("click", function () {
            var divContents = $("#container").html();
            var printWindow = window.open('', '', 'height=400,width=800');
            printWindow.document.write('<html><head><title>DIV Contents</title>');
            printWindow.document.write('</head><body >');
            printWindow.document.write(divContents);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.print();
        });
    </script>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 19px ;margin-top: 68px" >
<h2 style="color: white; font-family:inherit;">User Detail</h2></div>
<br>
<div class="container" id="container">
   
  	<b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=LIBView.USER_REGISTRATION_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.co.lib.mgt.bean.UserBean"
			scope="request"></jsp:useBean>
		
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>First Name</label>
								<h3 style="font-family: inherit;"><%=bean.getFirstName()%></h3>
							</div>
							<div class="col-sm-6 form-group">
								<label>Last Name</label>
								<h3 style="font-family: inherit;"><%=bean.getLastName()%></h3>
							</div>
						</div>	
									
							
					<div class="form-group">
						<label>Login Id</label>
						<h3 style="font-family: inherit;"><%=bean.getLogin()%></h3>
					</div>
						
				
					<div class="form-group">
						<label>Password</label>
							<h3 style="font-family: inherit;"><%=bean.getPassword()%></h3>
					</div>
								
						
					<div class="form-group">
						<label>Date Of Birth</label>
						<h3 style="font-family: inherit;"><%=DataUtility.getDateString(bean.getDob())%></h3>
					</div>
					<div class="form-group">
						<label>Email Id</label>
						<h3 style="font-family: inherit;"><%=bean.getEmailId()%></h3>
					</div>
					<div class="form-group">
						<label>Library Code</label>
					<h3 style="font-family: inherit;"><%=bean.getLibraryCode()%></h3>
					</div>
					<div class="form-group">
						<label>Mobile No.</label>
						<h3 style="font-family: inherit;"><%=bean.getMobileNo()%></h3>
					</div>
				
					</div>
					
				</form> 
				
				</div>
	</div>
		
	</div>
	<center>
	 <input type="button" class="btn btn-lg btn-info" value="Print Detail" id="btnPrint" />
	</center>
	
	
	<br><br>
	<hr>
	<br><br>
<%@include file="Footer.jsp"%>
</body>
</html>