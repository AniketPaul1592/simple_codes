<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.oms.model.ResigantionTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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
<br>
<br>
<br>
<%ResigantionTO rto= (ResigantionTO) session.getAttribute("details"); %>
<h1>Your application of resignation submitted successfully and you will get amount=
<%=rto.getAmount() %></h1>
</p>
</div>
</body>


</html>