
<%@include file="header.jsp"%>
          <!DOCTYPE html>
<div id="pro_container">
<div style="
    background-color: #F4F4F7;
    background-image: url('pro1.jpg');
    background-repeat: repeat;
    background-position: center;
    background-size: 1500px 500px;"
align="middle">
<fieldset> <legend>Login Form</legend>

<form>
<table align="center" border="30" cellspacing="20px" bgcolor="yellow">
 <tr>
  <td>Login ID</td>
  <td>
  <input type="text" name="login_id" 
  placeholder="Enter login id" 
  autofocus="autofocus">
  
  </td>
 </tr>
 
  <tr>
  <td>Password</td>
  <td><input type="password"  name="login_password" placeholder="Enter password"></td>
 </tr>
  <tr>
  <td colspan="2" align="right">
  <input type="submit" value="Login">
  </td>
   
 </tr>
  <tr>
  
 
</table>


</form>
<a href ="forget.html" style=font-size:30px >forget password ?</a>&nbsp;
 Click hear to<a href ="register.html" style=font-size:50px> sign up </a>for don't have an account?

</fieldset>


</div>
<%@include file="footer.jsp"%>
          

