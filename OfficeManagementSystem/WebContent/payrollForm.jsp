<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payroll Process</title>
<script type="text/javascript" src="js/validate_payrollform.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
 <link rel="stylesheet" href="/resources/demos/style.css" />
  <script type="text/javascript">
  function isDigit(employeeid)
  {
  	var flag=false;
  	var Number = /^[0-9]+$/; 
  	if(employeeid.match(Number))
  	{
  		flag=true;
  	}
  	else
  	{
  		flag=false;
  	}
  	return flag;
  }
 function checkId()
 {
	 var a;
	 a=document.getElementById("employeeid").value;
	 if(a.length>=1&&isDigit(a)){
 var xmlhttp;
 if (window.XMLHttpRequest)
   {// code for IE7+, Firefox, Chrome, Opera, Safari
   xmlhttp=new XMLHttpRequest();
   }
 else
   {// code for IE6, IE5
   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
   }

 xmlhttp.onreadystatechange=function()
   {
	 
	 
   if (xmlhttp.readyState==4 && xmlhttp.status==200)
     {
	   
	   
     document.getElementById("div1").innerHTML=xmlhttp.responseText;
     }
   
   };
 
   xmlhttp.open("GET","GettingEmployeeId?empId="+a,true);
   xmlhttp.send();
   

	 }

 }
 
 </script>
 <script type="text/javascript">
 function validateFirst(){
	 document.getElementById("enroll").disabled=true;
 }
 
 </script>
 <style type="text/css">
#ui-datepicker-div { font-size: 10px; } 
</style>
 
 <style>
div.transbox
{

  width: 450px;
  height: 650px;
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
<body  background="images\background_blur.jpg" onload="validateFirst();return checkId()" >
<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></td></tr>
</table>
<%
if(session!=null){
	if(session.getAttribute("role")==null){
		response.sendRedirect("login.jsp");
	
	}
else{
	 %>



<legend><center><h2><font color="Black">Payroll Form</font></h2></center></legend>
<form action="PayrollController" method="post" >


<% String message=(String)request.getAttribute("message");

if(message!=null)
	{
	%> 
<center>
<font style="Ariel" color="red"><b>
<%=message %>
</b></font></center>
<%} %>
<div class="transbox">
<p>
<div class="contained">
<table class="table table-striped table-bordered" align="center">
	<tr>
		<td>Employee ID</td>
		<td><input type="text" name="employeeid" id="employeeid" onkeypress="validate()" onkeydown="validate()" onkeyup="validate()" onblur="return checkId();" ></td>
		<td><font color="green">
		<div id="div1"></div>
		</font>
		</td>
	</tr>
	<tr><td></td><td>
	<font style="Ariel" color="red"><b>
		<span id="employeeiderror"></span>
		</b>
		</font>
		</td>
	</tr>
	<tr>
		<td>Designation</td>
		<td>
			<select name="designation" id="designation"  onchange="validate()" onkeyup="validate()" onkeypress="validate()">
				<option value="-1">Select</option>
				<option value="trainee">Trainee</option>
				<option value="software developer">Software Developer</option>
				<option value="senior software developer">Senior Software Developer</option>
				<option value="team lead">Team Lead</option>
				<option value="manager">Manager</option>
			</select></td>
		
	</tr>
	<tr><td></td><td>
	<font style="Ariel" color="red"><b>
		<span id="designationerror"></span>
		</b>
		</font></td>
	</tr>
	<tr>
		<td>Bank Name</td>
		<td><input type="text" name="bankname" id="bankname"  onkeydown="validate()" onkeyup="validate()" onkeypress="validate()"></td>
		
	</tr>
	<tr><td></td><td>
	<font style="Ariel" color="red"><b>
		<span id="banknameerror"></span>
		</b>
		</font></td>
	</tr>
	<tr>
		<td>Account No</td>
		<td><input type="text" name="accountno" id="accountno"  onkeydown="validate()" onkeyup="validate()" onkeypress="validate()"></td>
		
	</tr>
	<tr><td></td><td>
	<font style="Ariel" color="red"><b>
		<span id="accountnoerror"></span>
		</b>
		</font></td>
	</tr>
	<tr>
		<td>Policy No</td>
		<td><input type="text" name="policyno" id="policyno"  onkeydown="validate()" onkeyup="validate()" onkeypress="validate()"></td>
		
	</tr>
	<tr><td></td><td>
	<font style="Ariel" color="red"><b>
		<span id="policynoerror"></span>
		</b>
		</font></td>
	</tr>
	<tr>
		<td>Policy Name</td>
		<td><input type="text" name="policyname" id="policyname"  onkeydown="validate()" onkeyup="validate()" onkeypress="validate()"></td>
		
	</tr>
	<tr><td></td><td>
	<font style="Ariel" color="red"><b>
		<span id="policynameerror"></span>
		</b>
		</font></td>
	</tr>
	<tr>
		<td><input type="submit" id="enroll" class="btn btn-success " name="enroll" value="Enroll"></td>
		<td><input type="reset" class="btn btn-primary " name="reset" value="Reset" onclick="validate()"></td>
	</tr>
</table>
</div>
</p>
</div>
</form>
</body>
<%}
	}%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>