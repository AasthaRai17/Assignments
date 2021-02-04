package com.Planon.Bank;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;


//factory design pattern
public interface BankFunctions {
	
	/**
	 * function calculates the sum of all debits or credits as per the parameter passed for all the sources
	 * @param debitOrCredit is the type of transaction i.e. Credit or Debit
	 * @return Map<String, Double> -- <sourceId, amount> is the return format where amount is the sum.
	 */
	Map<String, Double> sumAllCreditsOrDebitsOfEachSource(TransactionType debitOrCredit);
	
	/**
	 * function will return all the months end balance sheet
	 * @return the data is form of TreeMap<String, TreeMap<String, Double>> which is <S1,<Jan2011, 12568>>
	 * 
	 */
	TreeMap<String, TreeMap<String, Double>> getEachMonthBalance();
	
	/**
	 * function will return the interest received for an account quaterly basis.
	 * where the interest is calculated on the avg balance of the quarter i.e. sum of balance per day/number of days in the quarter
	 * @return the data in format TreeMap<String, Double> which is <Year Quarter Source ,interest value>
	 */
	TreeMap<String, Double> getQuaterlyInterestOfAnAccount();
	
	/**
	 * function will iterate through the each day balance map and return the desired data for a specific time period in
	 * a specific format which needs to be casted.
	 * @param identifierMap will contain parameters to specify what time period data to get
	 * eg: identifierMap.put("GetData","Quaterly");identifierMap.put("TimePeriod","Q1");identifierMap.put("Year","2011");identifierMap.put("SourceId","S1");
	 * eg: identifierMap.put("GetData","Yearly");identifierMap.put("TimePeriod","2012");identifierMap.put("SourceId","S1");
	 * eg: identifierMap.put("GetData","Monthly");identifierMap.put("TimePeriod","JULY");identifierMap.put("SourceId","S1");identifierMap.put("Year","2011");
	 * eg: identifierMap.put("GetData","Source");identifierMap.put("SourceId","S1");
	 * @return the object data which needs to be casted. It gives multilevel map depending the data asked for
	 * eg: if Yearly, TreeMap<Month, TreeMap<Date, balance>> which is TreeMap<String, TreeMap<String, Double>>
	 */
	Object getDataForASpecificPeriod(HashMap<String, Object> identifierHashMap);
	
	/**
	 * @return each Day Balance in structure <S1,<2012,<January, <18, 12000>>>>
	 */
	TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> getEachDayBalance();
	
	/**
	 * simply returns the interest calculated
	 * @param principalAmount
	 * @param interestRate
	 * @return interest value
	 */
	static double calculateInterest(double principalAmount, float interestRate) {
		return principalAmount * (interestRate/100);
	}
}
