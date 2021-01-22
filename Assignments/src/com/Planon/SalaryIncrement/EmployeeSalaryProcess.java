package com.Planon.SalaryIncrement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.Planon.GenericUtilityFunctions.ValidationFunctions;

public class EmployeeSalaryProcess {

	private final static Logger logger = Logger.getLogger(EmployeeSalaryProcess.class.getName());
	SalaryConstants constants = new SalaryConstants();
	static final ValidationFunctions validations = new ValidationFunctions();

	/**
	 * landing function for salary processing
	 */
	public void inputSortAndPrintEmployee() {
		List<Employee> empList = new ArrayList<Employee>();
		empList = inputEmployee();
		printEmployeeSalary(empList);
	}

	/**
	 * Function to take input of employee name, salary and department
	 * @return List<Employee> which is the sorted list of employees based on the new salary
	 */
	public List<Employee> inputEmployee() {

		//using set so that duplicate objets can be avoided.
		Set<Employee> employeeList = new HashSet<>();
		List<Employee> sortedSet = new ArrayList<>();
		
		try {
			/*
			 * employeeList.add(new DeveloperEmployee("MonicaDev", 35000));
			 * employeeList.add(new DeveloperEmployee("MonicaDev", 45000));
			 * employeeList.add(new DeveloperEmployee("ChandlerDev", 25000));
			 * employeeList.add(new DeveloperEmployee("RossDev", 15000));
			 * 
			 * employeeList.add(new SalesEmployee("MonicaSales", 45000));
			 * employeeList.add(new SalesEmployee("JoeySales", 78000));
			 * employeeList.add(new SalesEmployee("JoeySales", 89000)); 
			 * employeeList.add(new SalesEmployee("RossSales", 100000));
			 */
			
			employeeList.add(new EmployeeAttributes("MonicaDev", constants.DEVELOPER, 35000));
			employeeList.add(new EmployeeAttributes("MonicaDev", constants.DEVELOPER, 45000));
			employeeList.add(new EmployeeAttributes("ChandlerDev", constants.DEVELOPER, 25000));
			employeeList.add(new EmployeeAttributes("RossDev", "developer", 15000));
			
			employeeList.add(new EmployeeAttributes("MonicaSales", constants.SALES, 45000));
			employeeList.add(new EmployeeAttributes("JoeySales", constants.SALES, 78000));
			employeeList.add(new EmployeeAttributes("JoeySales", constants.SALES, 89000));
			employeeList.add(new EmployeeAttributes("RossSales", constants.SALES, 100000));
			
			
			
			/*
			 * Set<EmployeeAttributes> employeeList = new HashSet<>(); 
			 * Scanner sc = new Scanner(System.in); 
			 * String empName = "", empDept = ""; 
			 * double empSalary; 
			 * int empCount=0;
			 * 
			 * System.out.println("Number of employees: "); 
			 * empCount = sc.nextInt();
			 * 
			 * System.out.println("Enter employee details: ");
			 * 
			 * try { 
			 * 		while(empCount!=0) {
			 * 				sc = new Scanner(System.in); 
			 * 				empName = sc.nextLine(); 
			 * 				empDept = sc.nextLine(); 
			 *				empSalary = sc.nextDouble();
			 * 
			 * 				if(validations.isItAValidName(empName) 
			 * 					&& validations.isItAValidName(empDept)
			 * 					&& validations.isItAValidSalary(empSalary) 
			 * 					&& validations.isItAValidDeptName(empDept)) 
			 * 
			 * 							employeeList.add(new EmployeeAttributes(empName, empDept, 35000));
			 * 
			 * 
			 * 				empCount--; 
			 * } 
			 * }catch(Exception e) { 
			 * 			logger.log(Level.INFO, "Exception while taking input:" + e); 
			 * 			e.printStackTrace(); 
			 * }
			 */
			
			
			//for sorting purpose using the list
			sortedSet = new ArrayList<Employee>(employeeList); 
			//sorting the list on the basis of employee salary
	        Collections.sort(sortedSet); 
	        
		}catch(Exception e) {
			logger.info("Exception in inputEmployee: " + e);
			e.printStackTrace();
		}
		
		return sortedSet;

	}

	/**
	 * function to print the employee details with their new incremented salary
	 * @param empList
	 */
	private void printEmployeeSalary(List<Employee> empList) {
		
		try {
			for(Employee empCtr : empList) {
		    	 System.out.println(empCtr.getEmpName() + " || " + empCtr.getEmpDept() + " || " + empCtr.getEmpNewSalary());
		     }
		}catch(Exception e) {
			logger.info("Exception in printEmployeeSalary: " + e);
			e.printStackTrace();
		}
	}

}
