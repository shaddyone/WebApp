package in.co.lib.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.lib.mgt.bean.BaseBean;
import in.co.lib.mgt.bean.BookBean;
import in.co.lib.mgt.exception.ApplicationException;
import in.co.lib.mgt.exception.DuplicateRecordException;
import in.co.lib.mgt.model.BookModel;
import in.co.lib.mgt.util.DataUtility;
import in.co.lib.mgt.util.DataValidator;
import in.co.lib.mgt.util.PropertyReader;
import in.co.lib.mgt.util.ServletUtility;

/**
 * Servlet implementation class BookCtl
 */
@WebServlet(name="BookCtl",urlPatterns={"/ctl/BookCtl"})
public class BookCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(BookCtl.class);

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("BookCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("bookName"))) {
            request.setAttribute("bookName",
                    PropertyReader.getValue("error.require", "Book Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("author"))) {
            request.setAttribute("author",
                    PropertyReader.getValue("error.require", "Author Name"));
            pass = false;
        }

        log.debug("BookCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookCtl populateBean method start");
		BookBean	bean=new BookBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBookName(DataUtility.getString(request.getParameter("bookName")));
		bean.setWriterName(DataUtility.getString(request.getParameter("author")));
		populateDTO(bean, request);
		log.debug("BookCtl populateBean method end");
		return bean;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   BookModel model = new BookModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				BookBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("BookCtl doGet method end");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("RoleCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			BookModel model=new BookModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				BookBean bean=(BookBean)populateBean(request);
					try {
						if(id>0){
						BookBean BBean=	model.findByPK(bean.getId());
						bean.setBookCode(BBean.getBookCode());
						model.update(bean);
						ServletUtility.setOpration("Edit", request);
						ServletUtility.setSuccessMessage("Data is successfully Updated", request);
		                ServletUtility.setBean(bean, request);

						}else {
							long pk=model.add(bean);
							//bean.setId(id);
							ServletUtility.setSuccessMessage("Data is successfully Saved", request);
							ServletUtility.forward(getView(), request, response);
						}
		              
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.forward(LIBView.ERROR_VIEW, request, response);
						return;
					
				} catch (DuplicateRecordException e) {
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Book Is already exists",
							request);
				}
				
			}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(LIBView.BOOK_CTL, request, response);
			return;
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("BookCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return LIBView.BOOK_VIEW;
	}

}
