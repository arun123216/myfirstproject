<%@include file="header.jsp"%>
          <!DOCTYPE html>
<div id="pro_container">

 <div style="
    background-color: #F4F4F7;
    background-image: url('pro1.jpg');
    background-repeat: repeat;
    background-position: right bottom;
    background-size: 1500px 900px;"
      align="middle">
     
<fieldset> <legend>Add Product</legend>

  <% if(request.getAttribute("errorCode")!=null){  %>
      <h1 style="color:red"><%=request.getAttribute("errorCode")%></h1>
   <%}%> 
<form method="post" action="addpro">
<table  align="center" border="30" cellspacing="20px" bgcolor="pink">
 
 
  
 <tr>
  <td>Product Name</td>
  <td><input name="product_name" placeholder="Enter Product Name"></td>
 </tr>
 <tr>
  <td>Price</td>
  <td><input  name="product_price" placeholder="Enter price"></td>
 </tr>
 <tr>
  <td>Dom</td>
  <td><input  name="product_dom" placeholder="dd/mm/yyyy"></td>
 </tr>
 
 
  <tr>
  <td>Details</td>
  <td><input name="product_details" placeholder="Details"></td>
 </tr>
  <td colspan="4" align="right">
  <input type="submit" value="Add product">
  </td>
   

  
  
 
</table>


</form>
                                 
 

</fieldset>
</div>
<%@include file="footer.jsp"%>