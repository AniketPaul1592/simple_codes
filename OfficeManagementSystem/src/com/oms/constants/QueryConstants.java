package com.oms.constants;

public class QueryConstants {
	
	//aniket
	//public static final String UPDATE_WorkAllocation = "insert into WORK_ALLOCATION values PROJ_ID=?, PROJ_TYPE=?, PROJ_ASSIGN_DATE=?, SHIFT=? , EMP_ID=?";
	public static final String INSERT_WorkAllocation="insert into WORK_ALLOCATION values (?,?,?,?,?)";
	public static final String UPDATE_Employee_Registration="update EMPLOYEE_REGISTRATION set MANAGER_ID=? where EMP_ID=?";
	public static final String SELECT_Details="select e.emp_id, e.emp_name ,e.manager_id, q.mname, w.proj_id, w.proj_type, w.proj_assign_date, w.shift from employee_registration e join (select s.emp_id as a, s.emp_name as mname from employee_registration s where emp_id=?)q on e.manager_id=q.a join work_allocation w on w.emp_id=e.emp_id";
	public static final String SELECT_Duration="select p.duration from project_details p join work_allocation w on p.project_id=w.proj_id join employee_registration e on e.emp_id=w.emp_id where e.emp_id=?";
	public static final String SELECT_Gender="select gender from employee_registration where emp_id=?";
	public static final String SELECT_TypeEmploy="select type_employement from employee_registration where emp_id=?";
	public static final String SELECT_DetailsNoManager="select e.emp_id, e.emp_name, w.proj_id, w.proj_type, w.proj_assign_date, w.shift from employee_registration e join work_allocation w on w.emp_id=e.emp_id where e.emp_id=?";
	public static final String SELECT_ValidateEmployee="select emp_id from employee_registration where emp_id=?";
	
	//sakshi
	public static final String SELECT_validate="select EMP_NAME from EMPLOYEE_REGISTRATION where EMP_ID=? and PWD=?";	
	public static final String SELECT_empExist="select EMPLOYEE_EXIST from EMPLOYEE_REGISTRATION where EMP_ID=?";
	public static final String SELECT_empname="select EMP_NAME from EMPLOYEE_REGISTRATION where EMP_ID=?";
	public static final String SELECT_emptype="select TYPE_EMPLOYEMENT from EMPLOYEE_REGISTRATION where EMP_ID=?";
	public static final String SELECT_pid="select PROJ_ID from WORK_ALLOCATION where EMP_ID=?";
	public static final String SELECT_mng="select mng.EMP_NAME  from EMPLOYEE_REGISTRATION emp join EMPLOYEE_REGISTRATION mng on mng.EMP_ID=emp.MANAGER_ID WHERE emp.EMP_ID=?";
	public static final String SELECT_mngid="select MANAGER_ID from EMPLOYEE_REGISTRATION where EMP_ID=?";
	public static final String INSERT_RESIGNATION="insert into RESIGNATION values(?,?,?,?,?,?,?)";
	public static final String SELECT_DOJ="select DATE_JOINING from EMPLOYEE_REGISTRATION where EMP_ID=?";
	public static final String SELECT_BASIC="select pm.BASIC from PAYROLL_MASTER pm join PAYROLL_ENROLL pe on pm.PAYROLL_ID=pe.PAYROLL_ID where pe.EMPLOYEE_ID=?";
	public static final String UPDATE_EMP_REG="update EMPLOYEE_REGISTRATION  set EMPLOYEE_EXIST='N' where EMP_ID=?";
	
	
	//subhendu
	
	public static final String CHECK_MANAGER="SELECT START_DATE,END_DATE FROM LEAVE_DETAILS WHERE EMP_ID=?";
	public static final String CHECK_LEAVE = "SELECT NO_OF_DAYS FROM LEAVE_MASTER WHERE LEAVE_TYPE=?";
	public static final String TAKEN_LEAVE = "SELECT NO_LEAVE_TAKEN  FROM LEAVE_DETAILS WHERE EMP_ID=? AND LEAVE_TYPE=?";
	public static final String INSERT_LEAVE = "INSERT INTO LEAVE_DETAILS VALUES (?,?,?,?,?,?,?)";
	public static final String SELECT_DATE = "select DATE_JOINING  from employee_registration where emp_id=?"; 
	public static final String SELECT_SEDATE = "select start_date,end_date  from leave_details where emp_id=? ";
	
	//arushi
	public static final String INSERT_payroll="insert into PAYROLL_ENROLL values(?,?,?,?,?,?)";
	public static final String SELECT_payroll="select payroll_id,designation,basic from PAYROLL_MASTER";
	public static final String SELECT_empdetails="select TYPE_EMPLOYEMENT,GENDER from EMPLOYEE_REGISTRATION where emp_id=?";
	public static final String SELECT_userdetails="select emp_id,password,role from LOGIN_MASTER";
	public static final String SELECT_employeedetails="select * from employee_registration where emp_id=?";
	public static final String SELECT_employee="select emp_id from employee_registration";
	public static final String SELECT_designation= "select emp_id,designation from employee_registration";
	
	//sachin
	public static final String INSERT_empREGULAR="insert into EMPLOYEE_REGISTRATION (EMP_ID,EMP_NAME,EMAIL_ID,PWD,PN_NO ,DOB,GENDER ,LOC ,EXP ,DESIGNATION,QUALIFICATION,TYPE_EMPLOYEMENT ,DATE_JOINING,EMPLOYEE_EXIST) values(empid_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_empCONTRACTOR="insert into EMPLOYEE_REGISTRATION (EMP_ID,EMP_NAME,EMAIL_ID,PWD,PN_NO ,DOB,GENDER ,LOC ,EXP ,DESIGNATION,QUALIFICATION,TYPE_EMPLOYEMENT ,DATE_JOINING,EMPLOYEE_EXIST) values(empid_seq1.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_empid="select EMP_ID from EMPLOYEE_REGISTRATION where EMAIL_ID=?";
	public static final String SELECT_email_id="select EMAIL_ID from employee_registration";
}
