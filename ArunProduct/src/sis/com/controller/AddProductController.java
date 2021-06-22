package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProductController
 */
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("add.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder errorCode = new StringBuilder();
		String  name= request.getParameter("product_name");
		String  details= request.getParameter("product_details");
		String  priceStr = request.getParameter("product_price");
         Float price=null;
         try {
			price=Float.parseFloat(priceStr);
		} catch (NumberFormatException e) {
			errorCode.append(" price is Not number "+ e.getMessage()+"<BR>");

		}
         
		
		String  domStr= request.getParameter("product_dom");
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date domUtil =null;
		//string to util. date 
		try {
			domUtil = sdf.parse(domStr);
		} catch (ParseException e) {
			errorCode.append("date is invalid format REQ DD/MM/YYYY "+ e.getMessage()+"<BR>");
		}
		
		//IF ERROR 
		if(errorCode.length()>0){
			request.setAttribute("errorCode", errorCode.toString());
			request.getRequestDispatcher("add.jsp").forward(request, response);
			return;
		}
		
		
		
		//date to long 
		Long domMs  =domUtil.getTime();
		//long to sql date 
		java.sql.Date domSql =new java.sql.Date(domMs);
		
		String addMsg="";
		//db insert
		
		Connection con = null;
		PreparedStatement pstmt  =null;

		//declare required type 
		String dbuser="system";
		String dbpassword="root";
		String url  = "jdbc:oracle:thin:@localhost:1521:XE";	

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con  = DriverManager.getConnection(url,dbuser,dbpassword);
/*	insert into sis_product(id,name,price,details,dom) 
			values(sis_product_seq.nextval,?,?,?,?);
*/
			String sql="insert into product1(id,name,price,details,dom)"
					+ " values(product1_seq.nextval,?,?,?,?)" ; 
			pstmt  = con.prepareStatement(sql);
			//set 
			pstmt.setString(1,name);
			pstmt.setFloat(2,price);
			pstmt.setString(3,details);
			pstmt.setDate(4,domSql);

			int result  = pstmt.executeUpdate();
		   if(result ==1){
			   addMsg = " Record  Added Successfuly ";
			}else{
			   addMsg = " Record  Not Added ";
			}


		}catch(ClassNotFoundException e){
		  errorCode.append("<h1 style='color:red'>Driver Not Loaded....." + e.getMessage()+"</h1>");
		}catch(SQLException e){
		  errorCode.append("<h1 style='color:red'>DB ERROR : " +e.getMessage()+"</h1>");
		  e.printStackTrace();
		}catch(Exception e){
		  errorCode.append("<h1 style='color:red'>Other ERROR " + e.getMessage()+"</h1>");
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

		//jdbc done
		//in redirect request attribute NOT WORK
		
		//session set  or query string(param) 
		//response.sendRedirect("showallproduct");
		//send msg with query string as request parameter
		response.sendRedirect("showallproduct?add_msg="+addMsg);
		//redirect show all product page
		//way2
		//or you can create one page which showm add mesg 
		//add_msg.jsp  and forward with attribue 
	}

}
