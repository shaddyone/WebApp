package in.co.lib.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.lib.mgt.bean.BaseBean;
import in.co.lib.mgt.bean.BookBean;
import in.co.lib.mgt.exception.ApplicationException;
import in.co.lib.mgt.model.BookModel;
import in.co.lib.mgt.util.DataUtility;
import in.co.lib.mgt.util.PropertyReader;
import in.co.lib.mgt.util.ServletUtility;

/**
 * Servlet implementation class BookListCtl
 */
@WebServlet(name = "BookListCtl", urlPatterns = { "/ctl/BookListCtl" })
public class BookListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(BookListCtl.class);
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookListCtl populateBean method start");
		BookBean bean = new BookBean();
		bean.setBookName(DataUtility.getString(request.getParameter("bookName")));
		bean.setBookCode(DataUtility.getLong(request.getParameter("bookCode")));
		bean.setWriterName(DataUtility.getString(request.getParameter("aouthor")));
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

		BookModel model = new BookModel();
		BookBean bean = (BookBean) populateBean(request);
		try {
			
			if (ids != null ) {
				BookBean deletebean = new BookBean();
				
					deletebean.setId(DataUtility.getInt(ids));
					model.delete(deletebean);
				
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
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

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BookBean bean = (BookBean) populateBean(request);

		BookModel model = new BookModel();
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
		return LIBView.BOOK_LIST_VIEW;
	}

}
