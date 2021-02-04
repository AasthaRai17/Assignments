package com.Planon.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Logger;

public class UtilityFunctionsForBankFunctions {
	
	static Logger logger = Logger.getLogger(UtilityFunctionsForBankFunctions.class.getName());
	
	/**
	 * function to calculate monthly balancesheet
	 * 
	 * @param monthEndBalance is a map for the balances <Source1, <July 2018, 6000>>
	 * @param oneTransaction  is one transaction form the list of transactions
	 */
	protected void checkForMonthlyBalance(TreeMap<String, TreeMap<String, Double>> monthEndBalance,
			Transaction oneTransaction) {

		try {
			String source = oneTransaction.getCustomerId();
			TransactionType tType = oneTransaction.gettType();
			Double amountFromTransaction = oneTransaction.gettAmount();
			String yearMonthKey = oneTransaction.gettDateAndTime().getYear() + " "
					+ oneTransaction.gettDateAndTime().getMonth();
			// 2018 July
			Double amount;

			// if my map already has the source then getting value from the map
			if (monthEndBalance.containsKey(source)) {
				TreeMap<String, Double> oneSourceAllMonthsMap = monthEndBalance.get(source);

				if (oneSourceAllMonthsMap.containsKey(yearMonthKey)) {
					Double amountFromMonthMap = oneSourceAllMonthsMap.get(yearMonthKey);

					amount = tType.equals(TransactionType.CREDIT) ? amountFromMonthMap + amountFromTransaction
							: amountFromMonthMap - amountFromTransaction;

				} else {// if a new month entry is being added
					// first entry for a month should be calculated wrt previous months entries as
					// per debit or credit so using the last entry's amount value as the map is
					// sorted
					// amount=?
					amount = tType.equals(TransactionType.CREDIT)
							? amountFromTransaction + oneSourceAllMonthsMap.lastEntry().getValue()
							: oneSourceAllMonthsMap.lastEntry().getValue() - amountFromTransaction;
				}
				oneSourceAllMonthsMap.put(yearMonthKey, amount);

			} else {// if map doesnt has the source adding a new map with the source
				amount = tType.equals(TransactionType.CREDIT) ? amountFromTransaction : -amountFromTransaction;

				TreeMap<String, Double> oneSourceAllMonthsMap = new TreeMap<>();
				oneSourceAllMonthsMap.put(yearMonthKey, amount);
				monthEndBalance.put(source, oneSourceAllMonthsMap);
			}
		} catch (Exception e) {
			logger.info("Exception in checkForMonthlyBalance: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * creating quarter based map with balance for each source
	 * @return
	 */
	public TreeMap<String, Double> getQuaterlyBalanceData(){
		
		TreeMap<String, Double> quarterlyBalanceMap = new TreeMap<>();
		try {
			
			ProcessTransaction.transactionList.forEach(oneTransaction -> {
				
				String quarter = ProcessTransaction.bankConst.MONTH_QUARTER_MAP.get(oneTransaction.gettDateAndTime().getMonth().toString());
				
				//S1 Q1 2012 Fixed Account
				String quarterKey = oneTransaction.gettSource() + " " + quarter + " "
						+ oneTransaction.gettDateAndTime().getYear() + " " + oneTransaction.getAccountType();
				
				double amount = 0;
				if (quarterlyBalanceMap.containsKey(quarterKey)) {
					amount = oneTransaction.gettType().equals(TransactionType.CREDIT)
							? quarterlyBalanceMap.get(quarterKey) + oneTransaction.gettAmount()
							: quarterlyBalanceMap.get(quarterKey) - oneTransaction.gettAmount();
				} else {
					amount = oneTransaction.gettType().equals(TransactionType.CREDIT) ? 
							oneTransaction.gettAmount() : 
							-oneTransaction.gettAmount();
				}
				quarterlyBalanceMap.put(quarterKey, amount);
			});
		}catch(Exception e) {
			logger.info("Exception in getQuaterlyBalanceData: " + e);
			e.printStackTrace();
		}
		return quarterlyBalanceMap;
	}
	
	/**
	 * function to calculate interest/quarter
	 * @param quarterlyBalanceMap
	 * @return 
	 */
	public TreeMap<String, Double> getInterestRateAndCalculateBalance(TreeMap<String, Double> quarterlyBalanceMap) {
		TreeMap<String, Double> avgQuarterBalanceMap = new TreeMap<>();
		try {
				quarterlyBalanceMap.forEach((quarterKey, balance) -> {
				int numberOfDays=0;
				if(checkLeapYear(Integer.parseInt(quarterKey.split(" ")[2])))
					numberOfDays= ProcessTransaction.bankConst.QUARTER_DAYS_MAP_LEAP.get(quarterKey.split(" ")[1]);
				else
					numberOfDays= ProcessTransaction.bankConst.QUARTER_DAYS_MAP_NON_LEAP.get(quarterKey.split(" ")[1]);
				
				String quarterIntKey = quarterKey.split(" ")[2] 
						+ " " + quarterKey.split(" ")[1] 
						+ " " + quarterKey.split(" ")[0];
				
				String accountType=quarterKey.split(" ")[3] + " " + quarterKey.split(" ")[4];
				/*
				 * AccountType accountType =
				 * AccountTypeFactory.getAccountType(quarterKey.split(" ")[3] + " " +
				 * quarterKey.split(" ")[4]); avgQuarterBalanceMap.put(quarterIntKey,
				 * BankFunctions.calculateInterest((balance/numberOfDays),
				 * accountType.getInterestRate()));
				 */
				InterestCalculationContext context = new InterestCalculationContext(new DefaultAccountInterestCalculation());
				if(BankConstants.SAVING_ACCOUNT.equalsIgnoreCase(accountType)) {
					context=new InterestCalculationContext(new SavingAccountInterestCalculation());
				}else if(BankConstants.CURRENT_ACCOUNT.equalsIgnoreCase(accountType)) {
					context=new InterestCalculationContext(new CurrentAccountInterestCalculation());
				}else if(BankConstants.FIXED_ACCOUNT.equalsIgnoreCase(accountType)) {
					context=new InterestCalculationContext(new FixedAccountInterestCalculation());
				}
				
				avgQuarterBalanceMap.put(quarterIntKey, context.calculateInterest((balance/numberOfDays)));
				
			});
			
		}catch(Exception e) {
			logger.info("Exception in checkForMonthlyBalance: " + e);
			e.printStackTrace();
		}
		return avgQuarterBalanceMap;
	}
	
	protected List<TreeMap<String, TreeMap<String, Double>>> returnSpecificMonthBalanceSheet(TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance,
			HashMap<String, Object> identifierMap) {
		List<TreeMap<String, TreeMap<String, Double>>> listOfMonthMaps = new ArrayList<>();
		try {
			
			//loop for months
			//create map with month key and map as value
			//add it in list
			//list of <Month, <date, amount>>
			((List<String>)identifierMap.get("Months")).forEach(monthName -> {
				listOfMonthMaps.add((TreeMap<String, TreeMap<String, Double>>) new TreeMap<>().put(monthName,
						(eachDayBalance.get(identifierMap.get("SourceId").toString())
			.get(identifierMap.get("Year").toString()).get(monthName))));
			});
			
		}catch(Exception e) {
			logger.info("Exception in returnSpecificMonthBalanceSheet: " + e);
			e.printStackTrace();
		}
		return listOfMonthMaps;
	}
	
	boolean checkLeapYear(int year) {
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
	}
}
