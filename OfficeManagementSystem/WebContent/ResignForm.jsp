<%@page import="com.oms.bo.ResignationBO"%>
<%@page import="com.oms.model.ResigantionTO"%>
<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	 a=document.getElementById("empid").value;
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/demos/style.css" />
 <style>
div.transbox
{

  width: 450px;
  height: 690px;
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
<style type="text/css">
#ui-datepicker-div { font-size: 10px; } 
</style>

<title>Resignation</title>
<script type="text/javascript">
function validate()
{
	
var idError1=document.getElementById("empid").value;
var doa=document.getElementById("doa").value;
var np=document.getElementById("np").value;
var comments1=document.getElementById("comments").value;
var Number = /^[0-9]+$/; 
var retVal=true;
if(idError1.length==0)
	{
		document.getElementById("idError").innerHTML="Employee id cannot be empty";
		retVal=false;
	}else if(idError1.length>7){
		
	document.getElementById("idError").innerHTML="Employee id should be 7 digits";
	retVal=false;
}
	else if(!document.form.empid.value.match(Number)){
		retVal=false;
		document.getElementById("idError").innerHTML="Employee id should be digits";
	}
	else{
		document.getElementById("idError").innerHTML=" ";
		
		
	}




if(doa.length==0)
{
	document.getElementById("doaError").innerHTML="Date of apply cannot be empty";
	retVal=false;
}
else{
	document.getElementById("doaError").innerHTML=" ";
	
	
}


if(comments1.length>=150)
{
document.getElementById("cError").innerHTML="comments cannot be more than 150";
retVal=false;
}
else{
	document.getElementById("cError").innerHTML=" ";
	
	
}




/* if(!document.form.empid.value.match(Number) && idError.length!=0 )
{
	
	document.getElementById("idError").innerHTML="Employee id should be digit";
	retVal=false;
} */

if(retVal==false){
	document.getElementById("submitID").disabled=true;
}
else{
	document.getElementById("submitID").disabled=false;
}
}

</script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
 <script type="text/javascript">
 function validateFirst(){
	 document.getElementById("submitID").disabled=true;
 }
 
 </script>

</head>
<body  background="images\background_blur.jpg"  onload="validateFirst()" >
<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
<legend><center><h2><font color="Black">Resign Form</font></h2></center></legend>

<br>
<br>
<br>

<%

if(session!=null){
	if(session.getAttribute("role")==null){
		response.sendRedirect("login.jsp");
	
	}
else{
	 
ResigantionTO rto=(ResigantionTO)session.getAttribute("details");
%>
<center>
<font style="Ariel" color="red"><b>
<%
String msg=null;
msg=(String) request.getAttribute("msg");
if(msg!=null)
{
	out.print(msg);
}
%>
</b></font>
<form name="form" action="ResignationController" method="post" >

<div class="transbox">
<p>
<div class="contained">
<table class="table table-striped table-bordered" align="center">

<tr>
<td>
Employee ID:
</td>
<td>
<input type="text" name="empid" id="empid" onkeydown="validate()" onkeyup="validate()" onkeypress="validate()" onblur="return checkId()">				
</td>
<td><font color="green">
		<div id="div1"></div>
		</font>
		</td>
</tr>
<tr>
<td>
</td>
<td><font style="Ariel" color="red"><b>
<span id="idError"></span>	
</font>
</td>
</tr>


<tr>
<td>
Employee Name:
</td>
<td>
 <input type="text" name="empname" class="form-control" readonly value=<%=rto.getEmpName()%> > 
</td>
</tr>
<tr>
<td>
Employee Type:
</td>
<td>
<input type="text" name="emptype" class="form-control" readonly value=<%=rto.getEmpType()%> >
</td>
</tr>
<tr>
<td>
Project Id:
</td>
<td>
<input type="text" name="pid" class="form-control" readonly value=<%=rto.getProjId()%>>
</td>
</tr>
<tr>
<td>
Manager Name:
</td>
<td>
<input type="text" name="mname" class="form-control" readonly value=<%=rto.getMgrName()%>>
</td>
</tr>

<tr>
<td>
Date Of Apply:
</td>
<td>
<input type="text" name="doa" id="doa"  onkeydown="validate()" onkeyup="validate()" onkeypress="validate()">
</td>
</tr>
<tr>
<td>
</td>
<td>
<font style="Ariel" color="red"><b>
<span id="doaError" ></span>
</b>
</font>
</td>
</tr>

<tr>
<td>
Notice Period :
</td>
<td>
<input type="text" name="np" id="np" value=2 class="form-control" readonly>
</td>
</tr>

<tr>
<td>
Comments :
</td>
<td>
<textarea rows="5" cols="30" name="comments" id="comments"  onkeyup="validate()" onselect="validate()" onkeydown="validate()" onchange="validate()" onkeypress="validate()" ></textarea>
</td>
</tr>
<tr>
<td>
</td>
<td>
<font style="Ariel" color="red"><b>
<span id="cError"></span>
</b>
</font>
</td>
</tr>
<tr>
<td>
<input type="submit" class="btn btn-success btn-sm" id="submitID" name="submit" value="submit">
</td>
<td>
<input type="reset" class="btn btn-primary btn-sm" name="reset" onclick="validate()">
</td>
</tr>
</table>
</div>
</p>
</div>
</form>
</center>
</body>
<%}} %>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>