package shopping.common.controller ;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuperClass implements SuperController, Validator {
	public final String CommandName = "/shopping/";
	public final String PREFIX = "err"; 
	private HttpServletRequest request = null;
	private HttpServletResponse response = null ;
	protected HttpSession session = null;

	@Override
	public boolean validate(HttpServletRequest request) {
		return false;
	}	

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doProcess(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doProcess(request, response);
	}	
	
	public void setErrorMessage(String message){
		this.request.setAttribute("errmsg", message );
	}

	public void GotoPage(String gotopage){ 
		RequestDispatcher dispatcher = request.getRequestDispatcher(gotopage);
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}