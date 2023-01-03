package in.co.lib.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.lib.mgt.bean.BaseBean;
import in.co.lib.mgt.bean.IssuBookBean;
import in.co.lib.mgt.exception.ApplicationException;
import in.co.lib.mgt.exception.DuplicateRecordException;
import in.co.lib.mgt.model.IssuBookModel;
import in.co.lib.mgt.util.DataUtility;
import in.co.lib.mgt.util.DataValidator;
import in.co.lib.mgt.util.PropertyReader;
import in.co.lib.mgt.util.ServletUtility;

/**
 * Servlet implementation class IssuBookCtl
 */
@WebServlet(name="IssuBookCtl",urlPatterns={"/ctl/IssuBookCtl"})
public class IssuBookCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(IssuBookCtl.class);

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("IssuBookCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("bookCode"))) {
            request.setAttribute("bookCode",
                    PropertyReader.getValue("error.require", "Book Code"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("studentCode"))) {
            request.setAttribute("studentCode",
                    PropertyReader.getValue("error.require", "Student Code"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("issuDate"))) {
            request.setAttribute("issuDate",
                    PropertyReader.getValue("error.require", "Issue Date"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("returnDate"))) {
            request.setAttribute("returnDate",
                    PropertyReader.getValue("error.require", "Return Date"));
            pass = false;
        }

        log.debug("IssuBookCtl validate method end");
        return pass;
    }

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookCtl populateBean method start");
		IssuBookBean	bean=new IssuBookBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		if (!"".equalsIgnoreCase(request.getParameter("bookCode"))
				&& !(DataValidator.isName(request.getParameter("bookCode")))
				&& (DataValidator.isLong(request.getParameter("bookCode"))))
			bean.setBookCode(DataUtility.getLong(request.getParameter("bookCode")));
		
		if (!"".equalsIgnoreCase(request.getParameter("studentCode"))
				&& !(DataValidator.isName(request.getParameter("studentCode")))
				&& (DataValidator.isInteger(request.getParameter("studentCode"))))
			bean.setStudentLibCode(DataUtility.getLong(request.getParameter("studentCode")));
		
		bean.setStudentLibCode(DataUtility.getLong(request.getParameter("studentCode")));
		bean.setIssuDate(DataUtility.getDate(request.getParameter("issuDate")));
		bean.setReturnDate(DataUtility.getDate(request.getParameter("returnDate")));
		populateDTO(bean, request);
		log.debug("BookCtl populateBean method end");
		return bean;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
			
		   IssuBookModel model = new IssuBookModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			long code = DataUtility.getLong(request.getParameter("c"));
			
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				
				try {
					IssuBookBean bean;
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
					
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}else if(code>0 ||op!=null){
				IssuBookBean Ubean=new IssuBookBean();
				Ubean.setBookCode(code);
				ServletUtility.setBean(Ubean, request);
			}
			
		
			ServletUtility.forward(getView(), request, response);
			ServletUtility.forward(getView(), request, response);
			log.debug("BookCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		IssuBookModel model=new IssuBookModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			IssuBookBean bean=(IssuBookBean)populateBean(request);
				try {
					
						
					

					
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					
	              
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
		return LIBView.ISSU_BOOK_VIEW;
	}

}
