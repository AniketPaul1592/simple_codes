<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
 <style>
div.transbox
{

  width: 450px;
  height: 480px;
  margin: 20px 470px;
  background-color: #F8F8F8 ; 
  border: 1px solid black;
  border-radius: 15px;
  opacity:0.59;
  filter:alpha(opacity=59); /* For IE8 and earlier */
}

div.transbox p
{
  margin: 10px 30px;
  font-weight: bold; 
  color: #000000;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body background="images\background_blur.jpg">
<legend><center><h2><font color="Black">Home Page</font></h2></center></legend>
<%
if(session!=null){
	if(session.getAttribute("role")==null){
		response.sendRedirect("login.jsp");
	}
	
else{
%>

<%
String role=(String)session.getAttribute("role");

if(role!=null){
%>

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
 <div class="transbox">
<p>
<div class="contained">
 <table align="center">
   <%
   if(role.equalsIgnoreCase("admin")){
   %>
  <tr><td>
<button type="button" onclick="window.location.href='EmployeeRegistration.jsp'" class="btn btn-primary">
Employee Registration
</button>
</td></tr>
<tr><td><br></td></tr>
<tr><td><br></td></tr>
  <tr><td>
<button type="button" onclick="window.location.href='workAllocation.jsp'" class="btn btn-success">
Work Allocation Registration
</button>
</td></tr>

  <%
  }
  %>
  <tr><td><br></td></tr>
<tr><td><br></td></tr>
  <tr><td>
<button type="button" onclick="window.location.href='leave.jsp'" class="btn btn-warning">
Leave Registration
</button>
</td></tr>
<tr><td><br></td></tr>
<tr><td><br></td></tr>  
   <%
   if(role.equalsIgnoreCase("admin")){
   %>
  <tr><td>
<button type="button" onclick="window.location.href='payrollForm.jsp'" class="btn btn-info">
Payroll Registration
</button>
</td></tr>
    
  <%} %>
  <tr><td><br></td></tr>
<tr><td><br></td></tr>
 <tr><td>
<button type="button" onclick="window.location.href='ResignForm.jsp'" class="btn btn-warning">
Resignation Registration
</button>
</td></tr>
   
<%}}} %>
<br>
<br>
<br>
</table>
</div>
</p>
</div>
<table align="center">
<tr><td><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>