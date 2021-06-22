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
<fieldset> <legend>User Registration Form</legend>

<form>
<table  align="center" border="30" cellspacing="20px" bgcolor="pink">
 <tr>
  <td>First Name</td>
  <td>
  <input type="text" name="first_name" 
  placeholder="Enter First_name" 
  autofocus="autofocus">
  
  </td>
 </tr>
 
  
 <tr>
  <td>Last Name</td>
  <td><input type="text"  name="Last_Name" placeholder="Enter Last_Name"></td>
 </tr>
 <tr>
  <td>Age</td>
  <td><input type="text"  name="User_Age" placeholder="Enter your_age"></td>
 </tr>
 <tr>
  <td>Dob</td>
  <td><input type="text"  name="User_dob" placeholder="Enter your Dob"></td>
 </tr>
 <tr>
  <td>Email</td>
  <td><input type="text"  name="email" placeholder="Enter your_email"></td>
 </tr>
 <tr>
  <td>login Id</td>
  <td><input type="text" maxlength="10" name="login_id" placeholder="Enter Login Id"></td>
 </tr>
 <tr>
  <td>Login password</td>
  <td><input type="password" maxlength="6" name="login_passord" placeholder="Enter password"></td>
 </tr>
  <tr>
  <tr>
  <td>Comform password</td>
  <td><input type="password"  name="comform password" placeholder="Enter again passord"></td>
 </tr>
  <td colspan="4" align="right">
  <input type="submit" value="Register">
  </td>
   
 </tr>
  <tr>
  
 
</table>


</form>
                                 
 Click hear to<a href ="login.html" style=font-size:50px  > sign in </a>for already  have an account?

</fieldset>



<%@include file="footer.jsp"%>
          