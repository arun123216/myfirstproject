package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sis.com.bo.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("login_id"); 
		String loginPassword = request.getParameter("login_password"); 
		

		  User loginUser  = null;
		  //validate in dabase
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
				 //declare required type 
			String user="system";
			String password="root";//if you password id diff change it
			String url  = "jdbc:oracle:thin:@localhost:1521:XE";	
		 
			StringBuilder errorCode = new StringBuilder("");
			 try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con  = DriverManager.getConnection(url,user,password);
				stmt  = con.createStatement();
				 
				String sql="select  * from  as_user where login_id='"+loginId+"' and login_password='"+loginPassword+"'" ; 
		     	 rs  =  stmt.executeQuery(sql);
				if( rs.next() ){
			          long id  =rs.getLong("id");
					  String name  = rs.getString("name");
					  String role  = rs.getString("role");
					  loginUser= new User();
					  loginUser.setId(id);
					  loginUser.setName(name);
					  loginUser.setLoginId(loginId);
					  loginUser.setLoginPassword(loginPassword);
					  loginUser.setRole(role);
				}
				
			 }catch(ClassNotFoundException e){
			   errorCode.append("Driver Not Loaded....." + e.getMessage());
			 }catch(SQLException e){
			   errorCode.append("DB ERROR : " +e.getMessage());
			   e.printStackTrace();
			 }catch(Exception e){
			   errorCode.append("Other ERROR " + e.getMessage());
			 }finally{
			     //release resoucer
			      if(con!=null){
				          try{
						     con.close();  //#5 close connection 
							}catch(SQLException e){
								errorCode.append("DB Con CLosing ERROR : "+ e.getMessage());
						  }//catch
				  }//if
			 }//finally
		  
		  
		//if user found means login successfull
			 //if user not found invalid user/password 

			 if(loginUser!=null){
				 //if user found means login successfull
			    //create session object
				 HttpSession session = request.getSession();
				//add required attribute for next service
				 session.setAttribute("user", loginUser);
				 //then send to main service page
				 //project page 
				 String projectPath = getServletContext().getContextPath();
				 response.sendRedirect(projectPath);
			 }else{
				 errorCode.append("invalide login id or password");
				 
				 request.setAttribute("loginError", errorCode.toString());
				 request.getRequestDispatcher("login.jsp").forward(request, response);
					
			 }
		
		
	}

}
