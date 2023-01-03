<%@page import="in.co.lib.mgt.controller.LoginCtl"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="in.co.lib.mgt.controller.LIBView"%>
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
<div style="width: 100%;background-color: #04091e;height: 62px; padding: 19px ;margin-top: 68px" >
<h3 style="color: white; font-family:inherit;">User Login</h3></div>
<br>
<div class="container">
    <h1 class="well">Login</h1>
     <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
      </font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=LIBView.LOGIN_CTL%>" method="post">
				
				<jsp:useBean id="bean" class="in.co.lib.mgt.bean.UserBean"
            scope="request"></jsp:useBean>
            
            <% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
              
               <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Login Id<font color="red">*</font></label>
								<input type="text" placeholder="Enter Login Id Here.." name="login" class="form-control" value="<%=DataUtility.getStringData(bean.getLogin())%>">
								<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Password<font color="red">*</font></label>
								<input type="password" placeholder="Enter Password Here.." name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword())%>">
								<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
						</div>					
						
						
					<input type="submit"
                        name="operation" class="btn btn-lg btn-info" value="<%=LoginCtl.OP_SIGN_IN%>" >					
					</div>
					
				</form> 
				</div>
	</div>
	</div>
	<br><br>
	<hr>
	
<%@include file="Footer.jsp"%>
</body>
</html>