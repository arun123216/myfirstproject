<%@include file="header.jsp"%>

<!--end header bottom-->
      <div id="pro_container">
	 
</div> 
<fieldset> <legend>Search By Product Id</legend>

<form>
<table align="left" border="30" cellspacing="20px" bgcolor="yellow">
 <tr>
  <td>Product ID</td>
  <td>
  <input type="text" name="p_id" 
  placeholder="Enter product Id" 
  autofocus="autofocus">
  
  </td>
  <td colspan="2" align="left">
  <input type="submit" value="search">
  </td>
   
 
   
 </tr>
  <tr>
  
 
</table>


</form>
<fieldset> <legend>Search By Product Name</legend>

<form>
<table align="middle" border="30" cellspacing="20px" bgcolor="yellow">
 <tr>
  <td>Product Name</td>
  <td>
  <input type="text" name="pname" 
  placeholder="Enter product Name" 
  autofocus="autofocus">
  
  </td>
  <td colspan="2" align="middle">
  <input type="submit" value="search">
  </td>
   
 </tr>
 </tr>
 
   
 </tr>
  <tr>
  
 
</table>


</form>


</fieldset>

</div>

<%@include file="footer.jsp"%>