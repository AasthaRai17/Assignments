package com.Planon.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BankConstants {
	static final String SAVING_ACCOUNT = "Saving Account";
	static final String FIXED_ACCOUNT = "Fixed Account";
	static final String CURRENT_ACCOUNT = "Current Account";
	static final int SAVING_RATE = 4;
	static final int CURRENT_RATE = 3;
	static final int FIXED_RATE = 5;
	static final Map<String, ArrayList<String>> MONTH_TO_QUARTER_MAP = new HashMap<>();
	static final Map<String, Integer> INTEREST_RATES = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	
	public BankConstants() {
		INTEREST_RATES.put(SAVING_ACCOUNT, SAVING_RATE);
		INTEREST_RATES.put(FIXED_ACCOUNT, FIXED_RATE);
		INTEREST_RATES.put(CURRENT_ACCOUNT, CURRENT_RATE);
		ArrayList<String> months = new ArrayList<>();
		months.add("APRIL");months.add("MAY");months.add("JUNE");
		MONTH_TO_QUARTER_MAP.put("Q1", months);
		months.clear();
		months.add("JULY");months.add("AUGUST");months.add("SEPTEMBER");
		MONTH_TO_QUARTER_MAP.put("Q2", months);
		months.clear();
		months.add("OCTOBER");months.add("NOVEMBER");months.add("DECEMBER");
		MONTH_TO_QUARTER_MAP.put("Q3", months);
		months.clear();
		months.add("JANUARY");months.add("FEBRUARY");months.add("MARCH");
		MONTH_TO_QUARTER_MAP.put("Q4", months);
		
	}
}
