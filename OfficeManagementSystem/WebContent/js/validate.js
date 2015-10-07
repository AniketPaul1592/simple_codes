/**
 * 
 */

function validateLeave(){
	
	
	var startDate = document.forms["frmLeave"]["startDate"].value;
	var date =startDate.split("-");
	var d=new Date();/*
	d.setDate(date[0]);
	d.setMonth(date[1], date[0]);*/
	var month=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var flag=0;
	for(var i=0;i<11;i++){
		if(date[1].search(month[i])){
			flag=i;
			break;
		}
	}
	d.setFullYear(parseInt(date[2]), i, parseInt(date[0]));
	
	var dd=new Date();
	
	alert(dd.getTime());
	return true;
}

