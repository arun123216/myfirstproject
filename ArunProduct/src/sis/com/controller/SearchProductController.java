package sis.com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sis.com.bo.Product;

/**
 * Servlet implementation class SearchProductController
 */
public class SearchProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productNameText = request.getParameter("name_search_text");
		 List<Product>productList  = new ArrayList<Product>();
			
		    StringBuilder errorCode = new StringBuilder();
			  //jdbc   db logic
				Connection con = null;
				Statement stmt  =null;
				ResultSet rs =null;
				//declare required type 
				String user="system";
				String password="root";
				String url  = "jdbc:oracle:thin:@localhost:1521:XE";	
			try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con  = DriverManager.getConnection(url,user,password);
					stmt  = con.createStatement();
					//******************************************
					String sql="select * from sis_product "
							+ " where lower(name) like lower('%"+productNameText+"%')"
							+ " order by id desc" ; 
					rs  =  stmt.executeQuery(sql);
					while( rs.next() ){
				          long id  =rs.getLong("id");
						  String name  = rs.getString("name");
						  String details  = rs.getString("details");
						  float price  =rs.getFloat("price");
						  java.sql.Date dom = rs.getDate("DOM");
						  java.sql.Timestamp created =rs.getTimestamp("RECORD_WHEN_CREATED");
						  
						  Product pro  = new Product();
						  pro.setId(id);
						  pro.setName(name);
						  pro.setPrice(price);
						  pro.setDetails(details);
						  pro.setDateOfManufacture(dom);
						  pro.setRecordCreated(created);
						  productList.add(pro);
					}//end while 
					
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
		
		//send search product in show all product 
			request.setAttribute("lastProductNameText",productNameText);
			request.setAttribute("errorCode",errorCode.toString());
			request.setAttribute("allproduct", productList);
			request.getRequestDispatcher("show_all_product.jsp").forward(request, response);
			
			
	}

}
