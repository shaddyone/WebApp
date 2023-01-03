package in.co.lib.mgt.controller;

public interface LIBView {
	
	public String APP_CONTEXT = "/LibraryMgt";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	public String BOOK_VIEW = PAGE_FOLDER + "/BookView.jsp";
	public String BOOK_LIST_VIEW = PAGE_FOLDER + "/BookListView.jsp";
	

	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	
	public String ISSU_BOOK_VIEW = PAGE_FOLDER + "/IssuBookView.jsp";
	
	public String ISSU_BOOK_LIST_VIEW = PAGE_FOLDER + "/IssuBookListView.jsp";
	
	
	
	public String STUDENT_DETAIL_VIEW = PAGE_FOLDER + "/StudentDetail.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW ="/WelcomeView.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	
	public String BOOK_CTL = APP_CONTEXT + "/ctl/BookCtl";
	public String BOOK_LIST_CTL = APP_CONTEXT + "/ctl/BookListCtl";
	
	public String ISSU_BOOK_CTL = APP_CONTEXT + "/ctl/IssuBookCtl";
	public String ISSU_BOOK_LIST_CTL = APP_CONTEXT + "/ctl/IssuBookListCtl";
	
	
	
    
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";



}
