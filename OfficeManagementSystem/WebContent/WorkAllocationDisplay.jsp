<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.oms.model.DisplayWorkAllocationTO" %>
    <%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Work Allocation</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/demos/style.css" />
 <style>
div.transbox
{

  width: 450px;
  height: 400px;
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
<%
if(session.getAttribute("role")==null){
	response.sendRedirect("login.jsp");
}
%>

<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>

<legend><center><h2><font color="Green" style="Ariel"><b>Work Allocation Details</b></font></h2></center></legend>
<%! String assignDate=null; 
String expCompletionDate=null;%>

<div class="transbox">
<p>
<div class="contained">
<table class="table table-striped table-bordered" align="center">

<tr><td><b>EmployeeID</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getEmployeeId()%></td></tr> 
<tr>
<td><b>Employee Name</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getEmployeeName()%></td></tr>
<tr>
<td><b>ManagerID</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getManagerId()%></td></tr>
<tr>
<td><b>Manager Name</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getManagerName()%></td></tr>
<tr>
<td><b>Project ID</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getProjectId()%></td></tr>
<tr>
<td><b>Project Type</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getProjectType()%></td></tr>
<tr>
<td><b>AssignDate</b></td>
<td><%SimpleDateFormat sd= new SimpleDateFormat("dd/MM/yyyy");
assignDate= sd.format(((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getAssignDate());%> <%=assignDate%></td></tr>
<tr>
<td><b>Shift</b></td>
<td><%=((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getShift()%></td></tr>
<tr>
<td><b>Expected Completion Date</b></td>
<td><%SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
expCompletionDate= sdf.format(((DisplayWorkAllocationTO)(request.getAttribute("daoObject"))).getExpCompletionDate());%> <%=expCompletionDate%></td></tr>
</table>
</div>
</p>
</div>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>