<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="com.oms.model.WorkAllocationTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Work Allocation</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/formCSS.css">

  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/demos/style.css" />
 <style type="text/css">
#ui-datepicker-div { font-size: 10px; } 
</style>
 
 
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
  <style>
    .datepicker{
     
    }
  </style>
 
  <script>
  $(function() {  
		$( "#date" ).datepicker({  
				changeMonth: true,      
				changeYear: true,
				dateFormat: 'dd/mm/yy',
				minDate: 0});  }); // min date 0 means previous date not eligible for selection
  </script>
<script type="text/javascript">
function notDigit(employeeid)
{
	
	var flag=false;
	var Number = /^[0-9]+$/; 
	if(employeeid.match(Number))
	{
		
		flag=false;
	}
	else
	{
		//alert(employeeid);
		flag=true;
	}
	
	return flag;
}
function validate()
{
	var name=document.getElementById("empId").value;
	/* var type=document.getElementById("projectType").value; */
	var project=document.getElementById("projectId").value;
	var manager=document.getElementById("managerId").value;
	var retVal=true;
	
 	if(name.length<1)
	{
	  document.getElementById("empIdError").innerHTML="Employee ID Cannot Be empty";
	  retVal=false;
	}
 	else if(name.length>7)
	{
		document.getElementById("empIdError").innerHTML="EmployeeID should be 7 digits";
		retVal=false;
	}
	else if((notDigit(name))){
		
		document.getElementById("empIdError").innerHTML="Invalid Employee Employee ID";
		
	}
 	else{
		document.getElementById("empIdError").innerHTML=" ";
	}
 	
 	
 	 if(document.getElementById("Offshore").checked) {
 		$("#proTypeError").hide();
 		}else if(document.getElementById("Onshore").checked) {
 			$("#proTypeError").hide();
 		}else
 			{
 			$("#proTypeError").show();
 			document.getElementById("proTypeError").innerHTML="Select a Project Type";
 			retVal=false;
 			
 			}
 	
 	
	if(project.length<1)
	{
	  document.getElementById("proIdError").innerHTML="Project ID Cannot Be empty";
	  retVal=false;
	}else{
		document.getElementById("proIdError").innerHTML=" ";
	}
	if(manager.length<1)
	{
	  document.getElementById("managerIdError").innerHTML="Manager ID Cannot Be empty";
	  retVal=false;
	}
	else if((notDigit(manager))){
		
		document.getElementById("managerIdError").innerHTML="Invalid Manager  ID";
		
	}
	else{
		document.getElementById("managerIdError").innerHTML=" ";
	}
	
	
	if(retVal==false){
		document.getElementById("submitID").disabled=true;
	}
	else{
		document.getElementById("submitID").disabled=false;
	}
}
</script>
 <script type="text/javascript">
 function validateFirst(){
	 document.getElementById("submitID").disabled=true;
 }
 
 </script>
</head>

<%
if(session!=null){
	if(session.getAttribute("role")==null){
		response.sendRedirect("login.jsp");
	
	}
else{
	 %>
<body  background="images\background_blur.jpg"  onload="validateFirst()">
<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
<legend><center><h2><font color="Black">Work Allocation</font></h2></center></legend>
<%String message=new String();
String managerMessage;
WorkAllocationTO wa=(WorkAllocationTO)(request.getAttribute("boObject"));
if(wa!=null){
 message=((WorkAllocationTO)(request.getAttribute("boObject"))).getMessage();
managerMessage=((WorkAllocationTO)(request.getAttribute("boObject"))).getManagerMessage();
if((("Employee").equals(message)))
 {
	 %>
<center> <font color="red" style="Ariel"><%= "Employee ID Not Valid" %> </font> </center>
<%
 }

 else if(managerMessage!=null||("Manager").equals(managerMessage))
 {
	 %>
<center> <font color="red" style="Ariel"><%= "Manager ID Not Valid" %> </font> </center>
<%
 }
}
 %>
<form action="WorkAllocationController" method="post">

<div class="transbox">
<p>
<div class="contained">
<table class="table table-striped table-bordered" align="center">

<tr><td><b>Employee ID:</b></td><td><input type="text" name="empId" id="empId"  onkeydown="validate()" onkeypress="validate()" onkeyup="validate()" ></td></tr>
<tr><td></td><td><font color="red" style="Ariel"><b><span id="empIdError"></span></b></font></td></tr>

<tr><td><b>Project ID:</b></td><td><input type="text" name="projectId" id="projectId"   onkeydown="validate()" onkeypress="validate()" onkeyup="validate()" ></td></tr>
<tr><td></td><td><font color="red" style="Ariel"><b><span id="proIdError"></span></b></font></td></tr>

<tr><td><b>Project Type:</b></td><td><input type="radio" name="projectType" id="Offshore" value="Offshore"   onchange="validate()" onkeypress="validate()" onclick="validate()" >Offshore &nbsp; <input type="radio" name="projectType" id="Onshore" value="Onshore"   onclick="validate()" onkeypress="validate()" onchange="validate()" >Onshore</td></tr>
<tr><td></td><td><font color="red" style="Ariel"><b><span id="proTypeError"></span></b></font></td></tr>

<tr><td><b>Manager ID:</b></td><td><input type="text" name="managerId" id="managerId"   onkeydown="validate()" onkeypress="validate()" onkeyup="validate()" ></td></tr>
<tr><td></td><td><font color="red" style="Ariel"><b><span id="managerIdError"></span></b></font></td></tr>

<tr><td><b>Project Assigned Date: </b></td><td><input type="text" class="datepicker" name="date" id="date"     ></td></tr>
<tr><td></td><td><span id="dateError"></span></td></tr>

<tr><td><b>Shift:</b></td><td><select name="shift" id="shift"   onchange="validate()" onkeypress="validate()" onclick="validate()" >
    <option value="Select">Select</option>
    <option value="Morning">Morning</option>
    <option value="Evening">Evening</option>
    <option value="Night">Night</option>
    </select></td></tr>

<tr><td><input type="submit" id="submitID" class="btn btn-success" name="submit" value="Allocate Project"></td>
    <td><input type="reset" class="btn btn-primary " name="reset" value="RESET" onchange="validate()" onclick="validate()" onkeypress="validate()" ></td></tr>
</table>
</div>
</p>

</div>
</form>
<a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a>
</body>
<%}} %>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>