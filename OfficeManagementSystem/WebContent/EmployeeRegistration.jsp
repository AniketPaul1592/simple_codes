
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>

  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

  <link rel="stylesheet" href="/resources/demos/style.css">
 <style type="text/css">
#ui-datepicker-div { font-size: 10px; } 
</style>
 
  <style>
  
div.transbox
{

  width: 450px;
  height: 880px;
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
 function validateFirst(){
	 document.getElementById("submitID").disabled=true;
 }
 
 </script>

  <script>

  $(function() {

    $( "#dob_datepicker" ).datepicker({
dateFormat: "dd/mm/yy",

      changeMonth: true,

      changeYear: true,
		maxDate: "-20Y"
    });

  });
  $(function() {

	    $( "#join_datepicker" ).datepicker({
	dateFormat: "dd/mm/yy",

	      changeMonth: true,

	      changeYear: true,
	      minDate: 0

	    });

	  });
  
  </script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<title> Registration</title>
<style>h5{color:red font:Arial fonttype:bold}</style>
<script type="text/javascript">
function validateEmployee()
{
	
	var name=document.getElementById("name").value;
	var email=document.getElementById("email").value;
	var contact=document.getElementById("contact").value;
	
    var password=document.getElementById("password").value;
    
    var joinDate=document.getElementById("join_datepicker").value;
    
    var birthDate=document.getElementById("dob_datepicker").value;
    
    
    var passwordRegex=/.*#.*/;
    var emailRegex=/.*@.*/;
    var nameRegex=/^[a-zA-Z\s]+$/;
    var contactRegex=/^[0-9]+$/;
    var retVal=true;
    var val=true;
    
    
  
  
     if(name.length<1||name==" ")
		{
    	 $("#employeeError").show();
		 document.getElementById("employeeError").innerHTML="Employee name field can not be blank!!";
		 retVal=false;
		}
     else if(!document.form.name.value.match(nameRegex))
	  {
   	 $("#employeeError").show();
 	   document.getElementById("employeeError").innerHTML="Employee Name must be character !!";
       retVal=false;
     }
     else
    	 {
    	 $("#employeeError").hide();
    	 }
     
     
	if(email.length<1)
	{
		 $("#emailError").show();
	 document.getElementById("emailError").innerHTML="Email-Id field can not be blank!!";
	 retVal=false;
	}
	 else if(!document.form.email.value.match(emailRegex))
	    {
		 $("#emailError").show();
	 	 document.getElementById("emailError").innerHTML="Email Id should contain @ !!";
	 	 retVal=false;
	 	
	 	}
	 else
		 {
		 $("#emailError").hide();
		 }
	if(contact.length<1)
		{
		$("#contactError").show();
		document.getElementById("contactError").innerHTML="Contact field can not be blank!!";
		retVal=false;
		}
	 else if(!document.form.contact.value.match(contactRegex))
     {
		 $("#contactError").show();
  	   document.getElementById("contactError").innerHTML="contact should only be digit !!";
     	retVal=false;
  	
  	  }
	else if((contact.length>=1)&&(contact.length<10))
		{
		$("#contactError").show();
		 document.getElementById("contactError").innerHTML="Contact number can not be less than 10 digits!!";
		 retVal=false;
		} 
	else if((contact.length>10))
		{
    	 $("#contactError").show();
		 document.getElementById("contactError").innerHTML="Contact number not more than 10 digits!!";
		 retVal=false;
		} 
	else
		{
		$("#contactError").hide();
		}
     if((birthDate.length<1))
		{
    	 $("#birthDateError").show();
		 document.getElementById("birthDateError").innerHTML="Birth Date should not be blank!!!";
		 retVal=false;
		} 
     else
    	 {
    	 $("#birthDateError").hide();
    	 }
     
     
    if(password.length<1)
    	{
    	 $("#passwordError").show();
    	document.getElementById("passwordError").innerHTML="password should not be blank";
    	retVal=false;
    	}
    else if(password.length<8)
	{
    	$("#passwordError").show();
	 document.getElementById("passwordError").innerHTML="password should  be of 8 character!!!";
	 retVal=false;
	}
    
    else if(!document.form.password.value.match(passwordRegex))
     {
    	$("#passwordError").show();
  	    document.getElementById("passwordError").innerHTML="password should contain # !!";
    	retVal=false;
  	
    }
    else
    	{
    	$("#passwordError").hide();
    	}
    if(document.form.location.value=='-1')
	{
		$("#locationError").show();
		document.getElementById("locationError").innerHTML="Select Location";
		retVal=false;
	}else
		{
		$("#locationError").hide();
		}
     if(document.getElementById("male").checked) {
		$("#GenderError").hide();

		document.getElementById("GenderError").innerHTML=" ";
		}else if(document.getElementById("female").checked) {
			$("#GenderError").hide();

			document.getElementById("GenderError").innerHTML=" ";
		}else
			{
			$("#GenderError").show();
			document.getElementById("GenderError").innerHTML="Select a Gender";
			retVal=false;
			
			}
    
    if(document.form.experience.value=='-1')
	{
		$("#experienceError").show();
		document.getElementById("experienceError").innerHTML="Select Experience";
		retVal=false;
	}else
		{
		$("#experienceError").hide();
		}
    if(document.form.highest_qualification.value=='-1')
	{
		$("#highestQualificationError").show();
		document.getElementById("highestQualificationError").innerHTML="Select Qualification";
		retVal=false;
	}else
		{
		$("#highestQualificationError").hide();
		}
    if(document.form.employee_type.value=='-1')
	{
		$("#employeeTypeError").show();
		document.getElementById("employeeTypeError").innerHTML="Select EmployeeType";
		retVal=false;
	}else
		{
		$("#employeeTypeError").hide();
		}
    if(joinDate.length<1)
    	{
    	$("#joinDateError").show();
    	 document.getElementById("joinDateError").innerHTML="Join Date can not be blank !!";
    	 retVal=false;
    	}
    else
    	{
    	$("#joinDateError").hide();
    	}
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




</head>

<%
if(session!=null){
	if(session.getAttribute("role")==null){
		response.sendRedirect("login.jsp");
	
	}
else{
	 %>

<body background="images\background_blur.jpg"   onload="validateFirst()" >

<center>
<table align="left" width="95%">
<tr><td ><ul class="nav nav-tabs" role="tablist">
  <li class="active"><a href="HomePage.jsp"><span class="glyphicon glyphicon-home"></span></a></li>
</ul></td>

<td align="right"><button type="button" onclick="window.location.href='LogoutController'" class="btn btn-primary"> Logout</button></a></td></tr>
</table>
<form name="form" action="RegistrationController"  method="post">

<legend><center><h2><font color="Black">Register Employee Here</font></h2></center></legend>
<font style="Ariel" color="red"><b>
<%if(request.getAttribute("messageRegistration")!=null){
	%>
	<%=
		request.getAttribute("messageRegistration")
	%>
	<% }%></b></font>


<div class="Contained">
<div class="transbox">
<p>
<table class="table table-striped table-bordered">
<tr><td><font color="black">Employee Name:</font></td><td><input type="text" name="name" id="name" onkeydown="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()"><h5><font color="red"><b><span 

id="employeeError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Email Id:</font></td><td><input type="text" name="email" id="email" onkeydown="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()"><h5><font color="red"><b><span 

id="emailError"></span>


</b></font></h5></td>

</tr>


<tr><td><font color="black">Contact:</font></td><td><input type="text" name="contact"  id="contact" onkeydown="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()"><h5><font color="red"><b><span 

id="contactError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Birth Date:</font><td><input type="text" name="birth_date" id="dob_datepicker" onclick="validateEmployee()" onkeypress="validateEmployee()" onchange="validateEmployee()"><h5><font color="red"><b><span 

id="birthDateError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Password:</font></td><td><input type="password" name="password" id="password" onkeydown="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()"><h5><font color="red"><b><span 

id="passwordError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Location:</font></td><td><select name="location" id="location" onchange="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()">
    <option value="-1">Select</option>
    <option value="Chennai">Chennai</option>
    <option value="Kolkata">Kolkata</option>
    <option value="Delhi">Delhi</option>
    <option value="Mumbai">Mumbai</option>
</select><h5><font color="red"><b><span id="locationError"></span></b></font></h5>
</td></tr>
<tr><td><font color="black">Gender:</font></td><td><input type="radio" name="gender" value="M" id="male" onclick="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()">M
<input type="radio" name="gender" value="F" id="female"  onclick="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()">F<h5><font color="red"><b><span id="GenderError"></span></b></font></h5>
<tr><td><font color="black">Experience:</font></td><td><select name="experience" id="experience"  onchange="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()">
    <option value="-1">Select</option>
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    </select><h5><font color="red"><b><span id="experienceError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Highest Qualification:</font></td><td><select name="highest_qualification" id="highest_qualification" onchange="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()">
    <option value="-1">Select</option> 
    <option value="B.tech">B.Tech</option>
    <option value="M.tech">M.Tech</option>
    <option value="MBA">MBA</option>
    </select><h5><font color="red"><b><span id="highestQualificationError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Employee Type:</font></td><td><select name="employee_type" id="employee_type"  onchange="validateEmployee()" onkeypress="validateEmployee()" onkeyup="validateEmployee()">
     <option value="-1">Select</option>
    <option value="Regular">Regular</option>
    <option value="Contractor">Contractor</option>
    </select><h5><font color="red"><b><span id="employeeTypeError"></span></b></font></h5></td></tr>
<tr><td><font color="black">Join date:</font></td><td><input type="text" name="join_date" id="join_datepicker" onclick="validateEmployee()" onchange="validateEmployee()" onkeyup="validateEmployee()"><h5><font color="red"><b><span 

id="joinDateError"></span></b></font></h5></td></tr>

<tr><td><input type="submit" id="submitID" class="btn btn-success btn-sm" name="register" value="Register"></td><td><input onclick="validateEmployee()" type="reset" class="btn btn-primary btn-sm" name="reset" 

value="Reset">
</td></tr>
</table>
</p>
</div>
</div>
</form></center>
<%}} %>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>