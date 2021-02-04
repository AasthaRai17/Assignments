package com.Planon.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.Planon.GenericUtilityFunctions.ValidationFunctions;

public class BankFunctionsImplementation implements BankFunctions {

	UtilityFunctionsForBankFunctions utilFn = new UtilityFunctionsForBankFunctions();
	static Logger logger = Logger.getLogger(BankFunctionsImplementation.class.getName());
	//ValidationFunctions utilityFunction = new ValidationFunctions();
	//BankConstants bankConst = new BankConstants();
	//ProcessTransaction processTransaction = new ProcessTransaction();
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Double> sumAllCreditsOrDebitsOfEachSource(TransactionType debitOrCredit) {

		Map<String, Double> sumOfAmountOfATransactionType = new TreeMap<>();

		try {

			ProcessTransaction.transactionList.stream().filter(oneTransaction -> (oneTransaction.gettType().equals(debitOrCredit)))
					.forEach(oneT -> {
						double amount = oneT.gettAmount();

						// if there is an existing amount entry for the source adding up the amount
						if (sumOfAmountOfATransactionType.containsKey(oneT.getCustomerId()))
							amount += sumOfAmountOfATransactionType.get(oneT.getCustomerId());

						// if the first entry directly put the amount value
						sumOfAmountOfATransactionType.put(oneT.getCustomerId(), amount);
					});

		} catch (Exception e) {
			logger.info("Exception in sumAllCreditsOrDebitsOfEachSource: " + e);
			e.printStackTrace();
		}
		return sumOfAmountOfATransactionType;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeMap<String, TreeMap<String, Double>> getEachMonthBalance() {
		// <S1,<Jan2011, 12568>>
		TreeMap<String, TreeMap<String, Double>> monthEndBalance = new TreeMap<>();
		try {
			
			// iterating through all the transactions
			ProcessTransaction.transactionList.forEach(oneTransaction -> {
				utilFn.checkForMonthlyBalance(monthEndBalance, oneTransaction);
			});

		} catch (Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
		return monthEndBalance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeMap<String, Double> getQuaterlyInterestOfAnAccount() {
		TreeMap<String, Double> quarterlyBalanceMap = utilFn.getQuaterlyBalanceData();
		return utilFn.getInterestRateAndCalculateBalance(quarterlyBalanceMap);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Object getDataForASpecificPeriod(HashMap<String, Object> identifierMap) {
		
		TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> returnObjet = new TreeMap<>();
		try {
			//<S1,<2012,<January, <18, 12000>>>>
			TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance = getEachDayBalance();
			
			String getDataType = "";
			
			if(ValidationFunctions.isValidstring(identifierMap.get("GetData"))
							&& ValidationFunctions.isValidstring(identifierMap.get("SourceId"))) {
				
				getDataType = ValidationFunctions.getStringValue(identifierMap.get("GetData"));
				
				if(getDataType.equalsIgnoreCase("Source")) {
					return eachDayBalance.get(ValidationFunctions.getStringValue(identifierMap.get("SourceId")));
				}
				else if(getDataType.equalsIgnoreCase("Yearly")
						&& ValidationFunctions.isValidstring(identifierMap.get("TimePeriod"))) {
						return eachDayBalance.get(ValidationFunctions.getStringValue(identifierMap.get("SourceId")))
								.get(ValidationFunctions.getStringValue(identifierMap.get("TimePeriod")));
				}
				else if(getDataType.equalsIgnoreCase("Monthly") 
						&& ValidationFunctions.isValidstring(identifierMap.get("TimePeriod"))
							&& ValidationFunctions.isValidstring(identifierMap.get("Year"))) {
						return eachDayBalance.get(ValidationFunctions.getStringValue(identifierMap.get("SourceId")))
								.get(ValidationFunctions.getStringValue(identifierMap.get("Year")))
								.get(ValidationFunctions.getStringValue(identifierMap.get("TimePeriod")));
				}
				else if(getDataType.equalsIgnoreCase("Quaterly")
						&& ValidationFunctions.isValidstring(identifierMap.get("TimePeriod"))
							&& ValidationFunctions.isValidstring(identifierMap.get("Year"))) {
						
						identifierMap.put("Months", 
								ProcessTransaction.bankConst.Q_MONTH_LIST.get(ValidationFunctions.getStringValue(identifierMap.get("TimePeriod"))));
						return utilFn.returnSpecificMonthBalanceSheet(eachDayBalance,identifierMap);
						
				}
				else if(getDataType.equalsIgnoreCase("HalfYearly")
							&& ValidationFunctions.isValidstring(identifierMap.get("Year"))) {
						
						List<String> months = new ArrayList<>();
						months.addAll(ProcessTransaction.bankConst.Q4_MONTHS);
						months.addAll(ProcessTransaction.bankConst.Q1_MONTHS);
						identifierMap.put("Months", months);
						return utilFn.returnSpecificMonthBalanceSheet(eachDayBalance,identifierMap);
				}
			}
			
		}catch(Exception e) {
			logger.info("Exception in getQuaterlyInteresetOfAnAccount: " + e);
			e.printStackTrace();
		}
		return returnObjet;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> getEachDayBalance() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance = new TreeMap<>();
		try {
			// calculating each day's balance
			// <S1,<2012,<January, <18, 12000>>>> format data in eachDayBalance
			ProcessTransaction.transactionList.forEach(oneTransaction -> {

				String year = String.valueOf(oneTransaction.gettDateAndTime().getYear());
				String month = oneTransaction.gettDateAndTime().getMonth().toString();
				String date = String.valueOf(oneTransaction.gettDateAndTime().getDayOfMonth());
				String sourcId = oneTransaction.gettSource();
				double amount = (oneTransaction.gettType().equals(TransactionType.CREDIT)) ? oneTransaction.gettAmount()
						: -oneTransaction.gettAmount();

				if (eachDayBalance.containsKey(sourcId)) {
					if (eachDayBalance.get(sourcId).containsKey(year)) {
						if (eachDayBalance.get(sourcId).get(year).containsKey(month)) {
							if (eachDayBalance.get(sourcId).get(year).get(month).containsKey(date))
								amount += eachDayBalance.get(sourcId).get(year).get(month).get(date);
							
							eachDayBalance.get(sourcId).get(year).get(month).put(date, amount);
						} else {
							TreeMap<String, Double> tempMap1 = new TreeMap<>();
							tempMap1.put(date, amount);
							eachDayBalance.get(sourcId).get(year).put(month, tempMap1);
						}
					} else {
						TreeMap<String, TreeMap<String, Double>> tempMap = new TreeMap<>();
						TreeMap<String, Double> tempMap1 = new TreeMap<>();
						tempMap1.put(date, amount);
						tempMap.put(month, tempMap1);
						eachDayBalance.get(sourcId).put(year, tempMap);
					}
				} else {
					TreeMap<String, TreeMap<String, TreeMap<String, Double>>> tempMap2 = new TreeMap<>();
					TreeMap<String, TreeMap<String, Double>> tempMap = new TreeMap<>();
					TreeMap<String, Double> tempMap1 = new TreeMap<>();
					tempMap1.put(date, amount);
					tempMap.put(month, tempMap1);
					tempMap2.put(year, tempMap);
					eachDayBalance.put(sourcId, tempMap2);
				}
			
			});
			
			
		} catch (Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
		return eachDayBalance;
	}

}
