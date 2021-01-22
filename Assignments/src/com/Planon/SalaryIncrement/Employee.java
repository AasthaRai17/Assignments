package com.Planon.SalaryIncrement;

public interface Employee extends Comparable{
	
	String getEmpName();
	String getEmpDept();
	double getEmpSalary();
	double getEmpNewSalary();
	void setNewSalary(String deptName);
	void setIncSalary();
	
	//defining static function in the interface for salary increment logic
	static double increaseSalary(double empSalary, double incrementFactor) {
		return empSalary + (empSalary * incrementFactor); 
	}
	 
}
