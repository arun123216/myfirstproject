package sis.com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutService
 */
public class LogoutService extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}else if(session==null){
		    //alread sessin expired	
		}
		//show message
		//show message and give home page and login page 
		//send home page 
		response.sendRedirect("login");
		
		 //include
		 //forward
		 //redirect 
		
	}

}
