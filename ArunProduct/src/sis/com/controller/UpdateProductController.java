package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sis.com.bo.Product;

/**
 * Servlet implementation class UpdateProductController
 */
public class UpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder errorCode = new StringBuilder();
		String updateMessge="";
		String  productIdStr= request.getParameter("id");

		long productId  = Long.parseLong(productIdStr);
		
		//id base 
		//db fetch record
		//create pojo  product obj
		//send to view 
		Product product = null;
		  //jdbc   db logic
			Connection con = null;
			PreparedStatement pstmt  =null;
			ResultSet rs =null;
			//declare required type 
			String user="system";
			String password="root";
			String url  = "jdbc:oracle:thin:@localhost:1521:XE";	
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con  = DriverManager.getConnection(url,user,password);
				String sql="select * from product1 where id=?" ; 
				pstmt  = con.prepareStatement(sql);
				pstmt.setLong(1,productId);
				//******************************************
				rs  =  pstmt.executeQuery();
				if( rs.next() ){
			          long id  =rs.getLong("id");
					  String name  = rs.getString("name");
					  String details  = rs.getString("details");
					  float price  =rs.getFloat("price");
					  java.sql.Date dom = rs.getDate("DOM");
					  java.sql.Timestamp created =rs.getTimestamp("RECORD_WHEN_CREATED");
					  
					  product  = new Product();
					  product.setId(id);
					  product.setName(name);
					  product.setPrice(price);
					  product.setDetails(details);
					  product.setDateOfManufacture(dom);
					  product.setRecordCreated(created);
					   
				}//end if
				
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
		request.setAttribute("errorCode", errorCode.toString());
		request.setAttribute("product",product);
		request.getRequestDispatcher("update.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //get all param
		 //parse 
		  //udpate into db
		  //send to showallpage  with msg
		
		StringBuilder errorCode = new StringBuilder();
		
		String  productIdStr= request.getParameter("product_id");
		long productId = Long.parseLong(productIdStr);
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
		
		//IF ERROR send udpate 
		if(errorCode.length()>0){
			request.setAttribute("errorCode", errorCode.toString());
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}
		
		
		
		//date to long 
		Long domMs  =domUtil.getTime();
		//long to sql date 
		java.sql.Date domSql =new java.sql.Date(domMs);
		
		String updateMessage="";
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
 
			String sql="update  product1"
					+ " set name=?,price=?,details=?,dom=? where id=?"; 
			pstmt  = con.prepareStatement(sql);
			//set 
			pstmt.setString(1,name);
			pstmt.setFloat(2,price);
			pstmt.setString(3,details);
			pstmt.setDate(4,domSql);
			pstmt.setLong(5, productId);

			int result  = pstmt.executeUpdate();
		   if(result ==1){
			   updateMessage = " Record  udpated Successfuly ";
			}else{
			   updateMessage = " Record  Not updated ";
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
		response.sendRedirect("showallproduct?update_msg="+updateMessage);
		
		
	}

}
