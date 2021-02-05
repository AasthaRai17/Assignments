package com.Planon.SalaryIncrement;

import com.Planon.GenericUtilityFunctions.ValidationFunctions;

public class EmployeeAttributes implements Employee{

	static final SalaryConstants constants = new SalaryConstants();
	static final ValidationFunctions validations = new ValidationFunctions();
	private String empName;
	private String empDept;
	private double empSalary;
	private double newEmpSalary;
	
	//parameterized constructor
	public EmployeeAttributes(String empName, String empDept, double empSalary) throws CustomExceptions {
		
		//the validations can be done while taking input
		//as in the code i m not taking input from console
		if(validations.isItAValidName(empName))
			this.empDept = empDept;
		else throw new CustomExceptions("Invalid Department Name: " + empDept);
		
		if(validations.isItAValidName(empName))
			this.empName = empName;
		else throw new CustomExceptions("Invalid Name: " + empName);
		
		if(validations.isItAValidSalary(empSalary))
			this.empSalary = empSalary;
		else throw new CustomExceptions("Invalid Salary: " + empSalary);
		//calculating the new salary while taking the initial input only
		setNewSalary(this.empDept);
	}
	

	@Override
	public String getEmpName() {
		return this.empName;
	}

	@Override
	public String getEmpDept() {
		return this.empDept;
	}

	@Override
	public double getEmpSalary() {
		return this.empSalary;
	}

	@Override
	public double getEmpNewSalary() {
		return this.newEmpSalary;
	}
	
	/**
	 * function is to calculate and set new salary depending on the department of the employee
	 */
	@Override
	public void setNewSalary(String empDept) {
		this.newEmpSalary = Employee.increaseSalary(empSalary, constants.SALARY_INC_MAPPING.get(empDept));
		
	}
	
	@Override
	public int compareTo(Object compareEmp) {
		double salary = ((EmployeeAttributes)compareEmp).getEmpNewSalary();
		return (int) (this.newEmpSalary-salary);
	}


	@Override
	public void setIncSalary() {
		// TODO Auto-generated method stub
		
	}


}
