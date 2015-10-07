<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/demos/style.css" />
 <style>
div.transbox
{

  width: 450px;
  height: 530px;
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
<title>Applying Leave</title>
<style type="text/css">
#ui-datepicker-div { font-size: 10px; } 
</style>

<script type="text/javascript" src="js/validate.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
  <script>

  
  
  
  $(function() {
		$( "#startDate" ).datepicker({
		dateFormat: "dd/mm/yy",
	    changeMonth: true,
	    onClose: function( selectedDate ) {
			 $( "#endDate" ).datepicker( "option", "minDate", selectedDate );
			},
	    changeYear: true});
		  });
  $(function() {
		$( "#endDate" ).datepicker({
		dateFormat: "dd/mm/yy",
	    changeMonth: true,
	    changeYear: true});
		  });
	
  

 

  
  function func(){
	  

	  if (myForm.knowledgeTransition.checked == 1){
          document.getElementById("ch_KT").innerHTML="<br><input id='kt'  name='kt' type='text'>";
		}else{
			document.getElementById("ch_KT").innerHTML="";
		}
	  }
  
</script>

<%@page import="com.oms.model.RegistrationTO"%>
</head>
<body  background="images\background_blur.jpg" >
<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
<legend><center><h2><font color="Black">Leave Application</font></h2></center></legend>
<br/>
<br/>

<%

%>
<%

if(session!=null){
	if(session.getAttribute("role")==null){
		response.sendRedirect("login.jsp");
	
	}

	 
else{
	RegistrationTO employee=(RegistrationTO)session.getAttribute("employees");
String name=employee.getEmployeeName();
String eType=employee.getEmployeeType();
long managerID=employee.getManagerId();
String message=new String();
message=(String)request.getAttribute("errorMessage");
%>
<span>
<font color="red">
<%if(message!=null){
	%>
<%=message %>
<%} %>
</font>
</span>
<form name="myForm"  action="LeaveController" method="post"  ng-app="" ng-controller="validateCtrl" novalidate ng-app="" ng-controller="validateCtrl" novalidate >

<div class="transbox">
<p>
<div class="contained">
<table class="table table-striped table-bordered" align="center">

<tr>
<td>Employee Name</td>
<td><input id="employeeName" class="form-control" name="employeeName"  type="text" value=<%= name%> readonly></td>
<td>
<font color="red" style="Ariel"><b><span  id="enameError"></span></b></font>
</td>

</tr>


<tr>
<td>Employee Type</td>
<td><input id="employeeType" class="form-control"  name="employeeType" type="text" value=<%= eType%> readonly></td>
</tr>

<tr>
<td>Manager ID</td>
<td><input id="managerID" class="form-control"  name="managerID"  type="text" value=<%= managerID%> readonly></td>
</tr>

<tr>
<td>Start Date</td>
<td><input id="startDate" name="startDate" type="text"  ng-model="startDate" required>

<br><b><font color="red">
<span ng-show="myForm.startDate.$invalid">
 <span  ng-show="myForm.startDate.$error.required">Start Date is required.
 </span></span></font></b>
</td>
</tr>



<tr>
<td>End Date</td>
<td><input id="endDate" name="endDate" type="text"  ng-model="endDate" required>
<br><b><font color="red">
<span ng-show="myForm.endDate.$invalid">
 <span  ng-show="myForm.endDate.$error.required">End Date is required.
 </span></span></font></b>
</td>

</td>
</tr>
<tr>

<tr>
<td>Leave Type:</td>
<td>
<select id="leaveType" name="leaveType" >
<%if(eType.equalsIgnoreCase("Regular")){ %>
   <option value="sick">Sick Leave</option>
   <%} %>
   <option value="casual">Casual Leave</option>
   <%if(eType.equalsIgnoreCase("Regular")){  %>
   <option value="earned">Earned Leave</option>
   <%} %>
</select>  
</td>
</tr>
<tr>
<td>Reason :</td>
<td><textarea id="reason" name="reason" ></textarea></td>
</tr>
<tr>
<td>Knowledge Trasition :</td>
<td><input onclick="func()" type="checkbox" name="knowledgeTransition" id="knowledgeTransition" value="1">
<div id="ch_KT" name="ch_KT"></div>
</td>
</tr>
<tr>
<td><input type="submit" class="btn btn-success btn-sm" value="Apply" ng-disabled="myForm.startDate.$invalid ||  
 myForm.endDate.$invalid" value="Apply Leave"></td>
<td><input type="reset" class="btn btn-primary btn-sm" value="Clear"></td>
</tr>

</table>
</div>
</p>
</div>
</form>

<%} }%>
<script>
function validateCtrl($scope) {
    $scope.startDate = '';
    $scope.endDate = '';
}
</script>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>