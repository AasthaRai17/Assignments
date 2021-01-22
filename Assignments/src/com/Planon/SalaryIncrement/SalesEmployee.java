package com.Planon.SalaryIncrement;

public class SalesEmployee implements Employee {
	
	static final SalaryConstants constants = new SalaryConstants();
	private String empName;
	private String empDept;
	private double empSalary;
	private double newEmpSalary;
	
	public SalesEmployee(String empName, double empSalary) {
		
		this.empDept = constants.SALES;
		this.empName = empName;
		this.empSalary = empSalary;
		setIncSalary();
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
	
	@Override
	public void setIncSalary() {
		this.newEmpSalary = Employee.increaseSalary(empSalary, constants.SALE_SAL_INC);
		
	}
	
	@Override
	public int compareTo(Object compareEmp) {
		double salary = 0;
		if (compareEmp instanceof SalesEmployee) {
			salary = ((SalesEmployee)compareEmp).getEmpNewSalary();
		}else if(compareEmp instanceof DeveloperEmployee) {
			salary = ((DeveloperEmployee)compareEmp).getEmpNewSalary();
		}else if(compareEmp instanceof OperationsEmployee) {
			salary = ((OperationsEmployee)compareEmp).getEmpNewSalary();
		}else if(compareEmp instanceof ManagerEmployee) {
			salary = ((ManagerEmployee)compareEmp).getEmpNewSalary();
		}
		return (int) (this.newEmpSalary-salary);
	}

	@Override
	public void setNewSalary(String deptName) {
		// TODO Auto-generated method stub
		
	}

}
