<%@page import="java.text.SimpleDateFormat"%>
<%@page import="sis.com.bo.Product"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>

<!--start container-->
<% 
String addMsg = request.getParameter("add_msg");
String deleteMsg = request.getParameter("delete_msg");
String updateMsg = request.getParameter("update_msg");

if(addMsg!=null ){ %>
<fieldset>
  <h1>
  Add msg ::<%=addMsg %>
 </h1>
</fieldset>
<%}%>

<%if(deleteMsg!=null ){ %>
<fieldset>
  <h1>
  Delete msg ::<%=deleteMsg %>
 </h1>
</fieldset>
<%}%>
<%if(updateMsg!=null ){ %>
<fieldset>
  <h1>
  Update msg ::<%=updateMsg %>
 </h1>
</fieldset>
<%}%>

<%

String errorMsg = (String) request.getAttribute("errorCode");
List<Product> productList =(List<Product>)request.getAttribute("allproduct");

%>
<div id="pro_container">

<div style="
    background-color: #F4F4F7;
    background-image: url('pro1.jpg');
    background-repeat: repeat;
    background-position: right bottom;
    background-size: 1500px 900px;"
align="middle">

<table  align="center" border="30" cellspacing="20px" bgcolor="#Fcb787"  width="100%"  text='blue'> 


<fieldset> <legend>Show All ProductS</legend>

 <input type="text" name="product_id" 
  placeholder="Enter product Id" 
  autofocus="autofocus">
  
  </td>
  <td colspan="2" align="left">
  <input type="submit" value="search">

<hr>
 </div>

<%@include file="footer.jsp"%>