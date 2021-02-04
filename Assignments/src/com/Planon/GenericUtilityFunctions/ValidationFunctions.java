package com.Planon.GenericUtilityFunctions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Planon.SalaryIncrement.SalaryConstants;

public class ValidationFunctions {
	
	public static final SalaryConstants salaryConstants = new SalaryConstants();
	
	public static boolean isItAValidName(String name){ 
  
        //if name is null return false else go for a pattern match
        if (null == name) { 
            return false; 
        } 
  
     // Regex to check valid name. 
        String regex = "^[A-Za-z]\\w{5,29}$"; 
        Pattern p = Pattern.compile(regex); 
        
        //matching the pattern
        Matcher m = p.matcher(name); 
  
        //return boolean if the pattern matches the name or not
        return m.matches(); 
    } 
	
	
	/**
	 * function to validate the salary input
	 * @param salary
	 * @return
	 */
	public static boolean isItAValidSalary(Double salary) {
		
		if(null == salary)
			return false;
		
		return salary <= 0;
	}
	
	
	/**
	 * function to validate restaurant review as a string less than 250 words.
	 * @param deptName
	 * @return
	 */
	public static boolean isItAValidDeptName(String deptName){ 
		  
        return (isItAValidName(deptName) 
        		&& salaryConstants.SALARY_INC_MAPPING.containsKey(deptName));
    } 
	
	
	
	public static boolean isItAValidReview(String review) {
		return (isItAValidName(review) && review.length() <= 250);
	}
	
	
	/**
	 * function to validate alpha numeric string with some special characters ( _ . - ,)
	 * @param alphaNumString
	 * @return
	 */
	public static boolean isItAValidAlphaNumericStringWithSpecialChar(String alphaNumString) {
        
        //if name is null return false else go for a pattern match
        if (null == alphaNumString)
            return false; 
  
     // Regex to check valid alphanumeric string. 
        String regex = "^[a-zA-Z0-9_.-,]*$"; 
        Pattern p = Pattern.compile(regex); 
        
        //matching the pattern
        Matcher m = p.matcher(alphaNumString); 
  
        //return boolean if the pattern matches the name or not
        return m.matches(); 
		
	}
	
	public static String getStringValue(Object o) {
		if(null != o)
			return o.toString();
		else
			return "";
	}
	
	public static boolean isValidstring(Object s) {
		return (null != s && s.toString().replace(" ", "").length() > 0);
	}
}
