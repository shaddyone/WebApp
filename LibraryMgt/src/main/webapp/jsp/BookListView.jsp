<%@page import="in.co.lib.mgt.bean.IssuBookBean"%>
<%@page import="in.co.lib.mgt.model.IssuBookModel"%>
<%@page import="in.co.lib.mgt.model.BookModel"%>
<%@page import="in.co.lib.mgt.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.lib.mgt.bean.BookBean"%>
<%@page import="in.co.lib.mgt.util.ServletUtility"%>
<%@page import="in.co.lib.mgt.controller.BookListCtl"%>
<%@page import="in.co.lib.mgt.controller.LIBView"%>
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
<h2 style="color: white; font-family: inherit;">User List</h2></div>
<br><br>
<form action="<%=LIBView.BOOK_LIST_CTL%>" method="post">
<div class="container">
	<div class="col-lg-12 well">
	<div class="row">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-3 form-group">
								<input type="text" placeholder="Enter Book Code Name Here.." name="bookCode" class="form-control" >
							</div>
							<div class="col-sm-3 form-group">
								<input type="text" placeholder="Enter Book Name Here.." name="bookName" class="form-control" >
							</div>
							<div class="col-sm-3 form-group">
								
								<input type="text" placeholder="Enter Author Name Here.." name="author" class="form-control" >
							</div>
							<div class="col-sm-3 form-group">
								
								<input type="submit"
                        name="operation" class="btn btn-md btn-info" value="<%=BookListCtl.OP_SEARCH%>" >
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
					<th scope="col">Book Code</th>
					<th scope="col">Book Name</th>
					<th scope="col">Author Name</th>
					<th scope="col">Click To Issue</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			 <%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				BookBean bean= null;
				List list = ServletUtility.getList(request);
				Iterator<BookBean> i = list.iterator();
				while (i.hasNext()) {
					bean = i.next();
				%> 
			<tbody>
				<tr>
					<th scope="row"><%=index++%></th>
					<td><%=bean.getBookCode()%></td>
					<td><%=bean.getBookName()%></td>
					<td><%=bean.getWriterName()%></td>
					<%
					
					IssuBookModel model=new IssuBookModel(); 
			    	IssuBookBean iBean=model.findByBookCode(bean.getBookCode());
			    	
							
							%>
							
					<%if(iBean!=null) {%>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="Issue"
						<%=(iBean != null) ? "disabled" : ""%>></td>
					
					<%}else{ %>
					<td><a href="IssuBookCtl?c=<%=bean.getBookCode()%>"  class="btn btn-success">Issue</a></td>
					<%} %>
					<td><a href="BookCtl?id=<%=bean.getId()%>"  class="btn btn-success" >Edit</a>
					<a href="BookListCtl?ids=<%=bean.getId()%>"  class="btn btn-danger" >DELETE</a>
					</td>
					</tr>
					<%} %>
				
				
			</tbody>
		</table>
		
		<table width="99%">
				<tr>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=BookListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>

				
					<%
						BookModel model = new BookModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-success"
						value="<%=BookListCtl.OP_NEXT%>"
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