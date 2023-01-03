<%@page import="in.co.lib.mgt.model.IssuBookModel"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.lib.mgt.bean.IssuBookBean"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@page import="in.co.lib.mgt.controller.IssuBookListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<h2 style="color: white; font-family: inherit;">Issue Book Record</h2></div>
<br><br>
<form action="<%=LIBView.ISSU_BOOK_LIST_CTL%>" method="post">
<div class="container">
	<div class="col-lg-12 well">
	<div class="row">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-3 form-group">
								<input type="text" placeholder="Enter Name Here.." name="studentName" class="form-control" >
							</div>
							<div class="col-sm-3 form-group">
								<input type="text" placeholder="Enter Book Name Here.." name="bookName" class="form-control" >
							</div>
							<div class="col-sm-3 form-group">
								
								<input type="text" placeholder="Enter Date Here.." name="issuDate" class="form-control" id="datepicker" readonly="readonly" >
							</div>
							<div class="col-sm-3 form-group">
								
								<input type="submit"
                        name="operation" class="btn btn-md btn-info" value="<%=IssuBookListCtl.OP_SEARCH%>" >
							</div>
						</div>					
			</div>		
		</div>
	</div>
</div>
<hr>
<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">S.No.</th>
					<th scope="col">Student Name</th>
					<th scope="col">Book Code</th>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Issue Date</th>
					<th scope="col">Return Date</th>
					<% UserBean uBean= (UserBean)session.getAttribute("user");%>
					<%if(uBean.getRoleId()==1){ %>
					<th scope="col">Action</th>
					<%} %>
					
				</tr>
			</thead>
			 <%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				IssuBookBean bean= null;
				List list = ServletUtility.getList(request);
				Iterator<IssuBookBean> i = list.iterator();
				while (i.hasNext()) {
					bean = i.next();
				%> 
			<tbody>
				<tr>
					<th scope="row"><%=index++%></th>
					<td><%=bean.getStudenName()%></td>
					<td><%=bean.getBookCode()%></td>
					<td><%=bean.getBookName()%></td>
					<td><%=bean.getWriterName()%></td>
					<td><%=DataUtility.getDateString(bean.getIssuDate())%></td>
					<td><%=DataUtility.getDateString(bean.getReturnDate())%></td>
					<% UserBean ubean= (UserBean)session.getAttribute("user");%>
					<%if(ubean.getRoleId()==1){ %>
					<td><a href="IssuBookListCtl?ids=<%=bean.getId()%>"  class="btn btn-danger" >DELETE</a></td>
					<%} %>
					</tr>
					<%} %>
				
				
			</tbody>
		</table>
		
		<table width="99%">
				<tr>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=IssuBookListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>

				
					<%
						IssuBookModel model = new IssuBookModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-success"
						value="<%=IssuBookListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>

				</tr>
			</table>
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<br><br><br>
	<%@ include file="Footer.jsp"%>
</body>
</html>