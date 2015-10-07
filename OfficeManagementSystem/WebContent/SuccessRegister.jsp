<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.Date" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrated!</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/demos/style.css" />
 <style>
div.transbox
{

  width: 450px;
  height: 250px;
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

</head>
<body  background="images\background_blur.jpg">
<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
<legend><center><h2><font color="Green" style="Ariel"><b>Successfully Registered</b></font></h2></center></legend>
<div class="transbox">
<p>
<center>
<h3><font color="black">Your Employee Id is :</font></h3> <%=(Long) request.getAttribute("empid")%>
<br><h3><font color="black">Designation is:</font></h3><%=(String) request.getAttribute("designation")%>
<%SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String retirementDate=sdf.format((Date) request.getAttribute("retirementDate"));
%>
<br><h3><font color="black">Retirement Date:</font></h3>
<%=retirementDate %>
</center>
</p>
</div>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>