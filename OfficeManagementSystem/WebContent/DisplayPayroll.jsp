<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.oms.model.PayrollTO"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payroll Details</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
 <link rel="stylesheet" href="/resources/demos/style.css" />
 <style>
div.transbox
{

  width: 450px;
  height: 350px;
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
<script type="text/javascript">
function myFunction() {
    window.print();
}
</script>
</head>
<body  background="images\background_blur.jpg">


<legend><center><h2><font color="Black">Payroll Details</font></h2></center></legend>

<% PayrollTO payrolldetails=(PayrollTO)request.getAttribute("payrolldetails"); %>

<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
<br/>
<br/>
<br/>

<div class="transbox">
<p>

<div class="contained">

<table border=0  class="table table-striped table-bordered" align="center">
<tr>
	<td>Employee id is </td>
	<td><%=payrolldetails.getEmployeeID() %></td>
</tr>
<tr>
	<td>Type (Regular / Contractor) </td>
	<td><%=payrolldetails.getEmployeeType() %></td>
</tr>
<tr>
	<td>Basic Salary is </td>
	<td><%=payrolldetails.getBasicSalary() %></td>
</tr>
<tr>
	<td>Gross Salary is </td>
	<td><%=payrolldetails.getGrossSalary() %></td>
</tr>
<tr>
	<td>Deduction is </td>
	<td><%=payrolldetails.getDeduction() %></td>
</tr>
<tr>
	<td>Tax is </td>
	<td><%=payrolldetails.getTax() %></td>
</tr>
<tr>
	<td>Net Salary is </td>
	<td><%=payrolldetails.getNetSalary() %></td>
</tr>
</table>
</div>
<center><button class="btn btn-success "  onclick="myFunction()">Print this page</button></center>


</div>

</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>
