package in.co.lib.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.lib.mgt.bean.BaseBean;
import in.co.lib.mgt.bean.BookBean;
import in.co.lib.mgt.bean.IssuBookBean;
import in.co.lib.mgt.bean.UserBean;
import in.co.lib.mgt.exception.ApplicationException;
import in.co.lib.mgt.model.BookModel;
import in.co.lib.mgt.model.IssuBookModel;
import in.co.lib.mgt.model.UserModel;
import in.co.lib.mgt.util.DataUtility;
import in.co.lib.mgt.util.PropertyReader;
import in.co.lib.mgt.util.ServletUtility;

/**
 * Servlet implementation class IssuBookListCtl
 */
@WebServlet(name = "IssuBookListCtl", urlPatterns = { "/ctl/IssuBookListCtl" })
public class IssuBookListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(IssuBookListCtl.class);
       
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookListCtl populateBean method start");
		IssuBookBean bean = new IssuBookBean();
		bean.setBookName(DataUtility.getString(request.getParameter("bookName")));
		bean.setIssuDate(DataUtility.getDate(request.getParameter("issuDate")));
		bean.setStudenName(DataUtility.getString(request.getParameter("studentName")));
		log.debug("BookListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		String ids = request.getParameter("ids");
		
		HttpSession session =request.getSession();
		
		UserBean ubean=(UserBean)session.getAttribute("user");
		
		IssuBookModel model = new IssuBookModel();
		IssuBookBean bean = (IssuBookBean) populateBean(request);
		try {
			
			if (ids != null ) {
				IssuBookBean deletebean = new IssuBookBean();
				
					deletebean.setId(DataUtility.getInt(ids));
					model.delete(deletebean);
				
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} 
			
			if(ubean.getRoleId()==2) {
				bean.setStudentLibCode(ubean.getLibraryCode());
			}
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("BookListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookListCtl doPost method start");
		List list = null;

		HttpSession session =request.getSession();
		
		UserBean ubean=(UserBean)session.getAttribute("user");
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		IssuBookBean bean = (IssuBookBean) populateBean(request);

		IssuBookModel model = new IssuBookModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {

				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(LIBView.BOOK_CTL, request, response);
			return;
		
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(LIBView.BOOK_LIST_CTL, request, response);
			return;

		}

		try {
			
			if(ubean.getRoleId()==2) {
				bean.setStudentLibCode(ubean.getLibraryCode());
			}

			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("BookListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return LIBView.ISSU_BOOK_LIST_VIEW;
	}

}
