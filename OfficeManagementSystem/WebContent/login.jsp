<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/formCSS.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
<title>Login Page</title>

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

function checkLength(){
	var eid=document.getElementById("userName").value;
	//alert(eid);
	if(eid.length==0){
		document.getElementById("errorEid").innerHTML=" ";
	}
	else if((eid.length>7)||(notDigit(eid))){
		
		document.getElementById("errorEid").innerHTML="Invalid Employee Employee ID";
		document.getElementById("login").disabled=true;
	}
	else{
		document.getElementById("errorEid").innerHTML=" ";
	}
	
}
</script>

</head>

<body background="images\background_blur.jpg">
<form name="myForm"  class="form-horizontal"  id="registerHere"
 method="post" action="LoginController" ng-app="" ng-controller="validateCtrl" novalidate>
 <fieldset>
 
<legend><center><h2><font color="Black">Login Employee</font></h2></center></legend>
<br>
<br>
<br>
<div class="transbox">
<p>
<table align="center">

<%
String message=(String)request.getAttribute("message");
if(message!=null)
{
	%>	
	
<font style="Ariel" color="Red">
<b>
<%=message %>
</b>
</font>	
	
	<%	
}
%>
<br>
<br>
<br>
<tr><td><div class="control-group">
<center> <label class="control-label">User Id</label>
 <div class="controls">
 <input type="text" class="input-xlarge" name="userName" id="userName" ng-model="userName" onkeyup="checkLength()"  onkeydown="checkLength()" onkeypress="checkLength()" required><br></center>
 <center><font color="Red" style="Ariel"><b>
 <span  ng-show="myForm.userName.$invalid">
 <span  ng-show="myForm.userName.$error.required">Employee ID is required.
 </span></span>
 
 <span id="errorEid" ></span>
 </b></font></center>
 </div>
 </div></td></tr>
 

<tr><td><div class="control-group">
 <center><label class="control-label">Password</label>
 <div class="controls">
 <input type="password" class="input-xlarge" name="password" id="password"  ng-model="password" required><br></center>
<center> <font color="Red" style="Ariel"><b><span ng-show="myForm.password.$invalid">
 <span   ng-show="myForm.password.$error.required">Password is required..
 </span></span></b></font></center>
 </div>
 </div></td></tr>
<!-- <tr><td><br></td></tr> -->
<tr><td><div class="control-group">
 <label class="control-label"></label>
 <div class="controls">
 <center><input value="Login" id="login"  type="submit" type="submit" class="btn btn-success btn-block" ng-disabled="myForm.userName.$invalid ||  
 myForm.password.$invalid">
 <br> <button type="reset" class="btn btn-primary " >Reset</button></center>
 </div>
 </div></td></tr>
 
 
</table>

</p>
</div>


</fieldset>

 </form>
<script>
function validateCtrl($scope) {
    $scope.userName = '';
    $scope.password = '';
}
</script>
</body>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>