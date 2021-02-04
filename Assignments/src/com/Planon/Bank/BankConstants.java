package com.Planon.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BankConstants {
	static final String SAVING_ACCOUNT = "Saving Account";
	static final String FIXED_ACCOUNT = "Fixed Account";
	static final String CURRENT_ACCOUNT = "Current Account";
	static final float SAVING_RATE = 4;
	static final float CURRENT_RATE = 3;
	static final float FIXED_RATE = 5;
	static final Map<String, Integer> QUARTER_DAYS_MAP_LEAP = new HashMap<>();
	static final Map<String, Integer> QUARTER_DAYS_MAP_NON_LEAP = new HashMap<>();
	static final Map<String, String> MONTH_QUARTER_MAP = new HashMap<>();
	static final Map<String, List<String>> Q_MONTH_LIST = new HashMap<>();
	static final Map<String, Float> INTEREST_RATES = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	static final List<String> Q1_MONTHS = new ArrayList<>();
	static final List<String> Q2_MONTHS = new ArrayList<>();
	static final List<String> Q3_MONTHS = new ArrayList<>();
	static final List<String> Q4_MONTHS = new ArrayList<>();
	
	public BankConstants() {
		INTEREST_RATES.put(SAVING_ACCOUNT, SAVING_RATE);
		INTEREST_RATES.put(FIXED_ACCOUNT, FIXED_RATE);
		INTEREST_RATES.put(CURRENT_ACCOUNT, CURRENT_RATE);
		MONTH_QUARTER_MAP.put("APRIL", "Q1");
		MONTH_QUARTER_MAP.put("MAY", "Q1");
		MONTH_QUARTER_MAP.put("JUNE", "Q1");
		MONTH_QUARTER_MAP.put("JULY", "Q2");
		MONTH_QUARTER_MAP.put("AUGUST", "Q2");
		MONTH_QUARTER_MAP.put("SEPTEMBER", "Q2");
		MONTH_QUARTER_MAP.put("OCTOBER", "Q3");
		MONTH_QUARTER_MAP.put("NOVEMBER", "Q3");
		MONTH_QUARTER_MAP.put("DECEMBER", "Q3");
		MONTH_QUARTER_MAP.put("JANUARY", "Q4");
		MONTH_QUARTER_MAP.put("FEBRUARY", "Q4");
		MONTH_QUARTER_MAP.put("MARCH", "Q4");
		
		QUARTER_DAYS_MAP_LEAP.put("Q1", 91);
		QUARTER_DAYS_MAP_LEAP.put("Q2", 91);
		QUARTER_DAYS_MAP_LEAP.put("Q3", 92);
		QUARTER_DAYS_MAP_LEAP.put("Q4", 92);
		QUARTER_DAYS_MAP_NON_LEAP.put("Q1", 90);
		QUARTER_DAYS_MAP_NON_LEAP.put("Q2", 91);
		QUARTER_DAYS_MAP_NON_LEAP.put("Q3", 92);
		QUARTER_DAYS_MAP_NON_LEAP.put("Q4", 92);
		
		Q1_MONTHS.add("APRIL");Q1_MONTHS.add("MAY");Q1_MONTHS.add("JUNE");
		Q2_MONTHS.add("JULY");Q2_MONTHS.add("AUGUST");Q2_MONTHS.add("SEPTEMBER");
		Q3_MONTHS.add("OCTOBER");Q3_MONTHS.add("NOVEMBER");Q3_MONTHS.add("DECEMBER");
		Q4_MONTHS.add("JANUARY");Q4_MONTHS.add("FEBRUARY");Q4_MONTHS.add("MARCH");
		Q_MONTH_LIST.put("Q1", Q1_MONTHS);
		Q_MONTH_LIST.put("Q2", Q2_MONTHS);
		Q_MONTH_LIST.put("Q3", Q3_MONTHS);
		Q_MONTH_LIST.put("Q4", Q4_MONTHS);
	}
}
