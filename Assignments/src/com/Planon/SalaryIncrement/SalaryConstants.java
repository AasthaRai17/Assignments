package com.Planon.SalaryIncrement;

import java.util.HashMap;
import java.util.TreeMap;

public class SalaryConstants {

	public final String DEVELOPER = "Developer";
	public final String MANAGER = "Manager";
	public final String SALES = "Sales";
	public final String OPERATIONS = "Operations";
	public final double DEV_SAL_INC = 0.15;
	public final double SALE_SAL_INC = 0.10;
	public final double MANAGER_SAL_INC = 0.12;
	public final double OPER_SAL_INC = 0.08;
	
	//making the map case insensitive so that developer or Developer or DEVELOPER are treated as one dept name and null pointer can be prevented
	public final TreeMap<String, Double> SALARY_INC_MAPPING = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	
	public SalaryConstants() {
		SALARY_INC_MAPPING.put(DEVELOPER, DEV_SAL_INC);
		SALARY_INC_MAPPING.put(MANAGER, MANAGER_SAL_INC);
		SALARY_INC_MAPPING.put(SALES, SALE_SAL_INC);
		SALARY_INC_MAPPING.put(OPERATIONS, OPER_SAL_INC);
	}

}
