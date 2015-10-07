/**
 * 
 */

function validate()
{
	
	
	var employeeid = document.getElementById("employeeid").value;
	
	var designation = document.getElementById("designation").value;

	var bankname = document.getElementById("bankname").value;
	
	var accountno = document.getElementById("accountno").value;
	var policyno = document.getElementById("policyno").value;
	var policyname = document.getElementById("policyname").value;
	var designation = document.getElementById("designation").value;
	
	var retVal=true;
	if(employeeid.length<1)
	{
		document.getElementById("employeeiderror").innerHTML="EmployeeID field can not be blank!!";
		retVal=false;
	}
	else if(employeeid.length>7)
	{
		document.getElementById("employeeiderror").innerHTML="EmployeeID should be 7 digits";
		retVal=false;
	}
	else
	{
		if(isDigit(employeeid)==false)
		{
			document.getElementById("employeeiderror").innerHTML="Digits Only";
		}
		else
		{
			document.getElementById("employeeiderror").innerHTML="";
		}
	}
	
	if(designation=='-1')
	{
		document.getElementById("designationerror").innerHTML="Designation field can not be blank!!";
		retVal=false;
	}
	else{
		document.getElementById("designationerror").innerHTML=" ";
	}
	
	if(bankname.length<1)
	{
		document.getElementById("banknameerror").innerHTML="Bank Name field can not be blank!!";
		retVal=false;
	}
	else
	{
		if(isAlphabet(bankname)==false)
		{
			document.getElementById("banknameerror").innerHTML="Alphabets Only";
		}
		else{
			document.getElementById("banknameerror").innerHTML="";
		}
	}
	
	if(accountno.length<1)
	{
		document.getElementById("accountnoerror").innerHTML="Account No field can not be blank!!";
		retVal=false;
	}
	else
	{
		if(accountnolength()==false)
		{
			document.getElementById("accountnoerror").innerHTML="Account No should be of 14 digits";
		}
		else{
			document.getElementById("accountnoerror").innerHTML="";
		}
	}
	
	if(policyno.length<1)
	{
		document.getElementById("policynoerror").innerHTML="Policy Number field can not be blank!!";
		retVal=false;
	}
	else
	{
		if(policynolength()==false)
		{
			document.getElementById("policynoerror").innerHTML="Policy No should be minimum 6 digits and maximum 10 digits";
		}
		else{
			document.getElementById("policynoerror").innerHTML=" ";
		}
	}
	
	if(policyname.length<1)
	{
		document.getElementById("policynameerror").innerHTML="Policy Name field can not be blank!!";
		retVal=false;
	}
	else
	{
		if(isAlphabet(policyname)==false)
		{
			document.getElementById("policynameerror").innerHTML="Alphabets Only";
		}
		else{
			document.getElementById("policynameerror").innerHTML=" ";
		}
	}
if(retVal==false){
	document.getElementById("enroll").disabled=true;
	
}
else{
	document.getElementById("enroll").disabled=false;
	
}
}


function policynolength()
{
	var flag=false;
	var policyno = document.getElementById("policyno").value;
	if(policyno.length>=6&&policyno.length<=10)
	{
		flag=true;
	}	
	else
	{
		document.getElementById("policynoerror").innerHTML="Policy No should be minimum 6 digits and maximum 10 digits";
	}
	return flag;
}

function accountnolength()
{
	var flag=false;
	var accountno = document.getElementById("accountno").value;
	if(accountno.length==14)
	{
		flag=true;
	}
	else
	{
		document.getElementById("accountnoerror").innerHTML="Account No should be of 14 digits";
		flag=false;
	}
	return flag;
}

function isAlphabet(textfield)
{
	var flag=false;
	var Alphabet = /^[a-zA-Z]+$/;
	if(textfield.match(Alphabet))
		{
			flag=true;
		}
	else
		{
			flag=false;
		}
	return flag;
}

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


