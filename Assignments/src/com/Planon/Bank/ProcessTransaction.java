package com.Planon.Bank;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class ProcessTransaction {

	static List<Transaction> transactionList = new ArrayList<>();
	static Logger logger = Logger.getLogger(ProcessTransaction.class.getName());
	private static DecimalFormat df = new DecimalFormat("0.00");
	//take input
	//sum all credits of all source/destination
	//get balance sheet for all source/destination
	
	/**
	 * Function will calculate the month end balance of all sources
	 */
	private void getEachMonthBalance() {
		try {
			//<S1,<Jan2011, 12568>>
			Map<String, Map<String, Double>> monthEndBalance = new TreeMap<>();
			Map<String, Double> monthlyMap = new TreeMap<>();
			Map<String, Double> localMapForCalculation = new TreeMap<>();
			//iterating thorugh all the transactions
			transactionList
			.forEach(oneTransaction -> {
				checkForMonthlyBalance(monthEndBalance, oneTransaction, monthlyMap, localMapForCalculation);
			});
			
			//printing the details
			Map<String, Double> monthMap = new HashMap<>();
			for(Entry<String, Map<String, Double>> entry : monthEndBalance.entrySet()) {
				System.out.println(entry.getKey()+": ");
				monthMap = monthEndBalance.get(entry.getKey());
				
				for(Entry<String, Double> entry1 : monthMap.entrySet()) {
					System.out.println(entry1.getKey()+"::::: " + df.format(entry1.getValue()));
				}
			}
			
		}catch(Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * function to calculate month end balance
	 * @param monthEndBalance 
	 * @param oneTransaction
	 * @param monthlyMap
	 * @param localMapForCalculation
	 */
	private void checkForMonthlyBalance(Map<String, Map<String, Double>> monthEndBalance, Transaction oneTransaction, Map<String, Double> monthlyMap, Map<String, Double> localMapForCalculation) {
		
		try {
			LocalDateTime dateOfTransaction = oneTransaction.gettDateAndTime();
			String monthAndYearKey = dateOfTransaction.getMonth() + " " + dateOfTransaction.getYear();
			Double amount = 0.0;
			
			/*if(localMapForCalculation.containsKey(oneTransaction.gettSource())){
				Double amt = localMapForCalculation.get(oneTransaction.gettSource());
				amount+=amt;
			}*/
			
			if(monthEndBalance.containsKey(oneTransaction.gettSource())) {
				monthlyMap=monthEndBalance.get(oneTransaction.gettSource());
				//get month and year of the transaction
				
				if(monthlyMap.containsKey(monthAndYearKey)
						&& oneTransaction.gettType().equals(TransactionType.CREDIT)) {
					amount = monthlyMap.get(monthAndYearKey);
					amount+=oneTransaction.gettAmount();
				}
				else if(monthlyMap.containsKey(monthAndYearKey)) {
					amount = monthlyMap.get(monthAndYearKey);
					amount-=oneTransaction.gettAmount();
				}
				else if(oneTransaction.gettType().equals(TransactionType.CREDIT))
					amount+=oneTransaction.gettAmount();
				else
					amount-=oneTransaction.gettAmount();
					
				monthEndBalance.get(oneTransaction.gettSource()).put(monthAndYearKey, amount);
			}else {
				if(oneTransaction.gettType().equals(TransactionType.CREDIT)) {
					amount+=oneTransaction.gettAmount();
					monthlyMap.put(monthAndYearKey, amount);
					monthEndBalance.put(oneTransaction.gettSource(), monthlyMap);
				}
				else {
					amount-=amount-oneTransaction.gettAmount();
					monthlyMap.put(monthAndYearKey, amount);
					monthEndBalance.put(oneTransaction.gettSource(), monthlyMap);
				}
			}
			
			/*if(!localMapForCalculation.containsKey(oneTransaction.gettSource())){
				localMapForCalculation.put(oneTransaction.gettSource(), amount);
			}else {
				localMapForCalculation.put(oneTransaction.gettSource(), localMapForCalculation.get(oneTransaction.gettSource())+amount);
			}*/
			
			monthlyMap = new TreeMap<>();
			localMapForCalculation = new TreeMap<>();
		}catch(Exception e) {
			logger.info("Exception in checkForMonthlyBalance: " + e);
			e.printStackTrace();
		}
}
	private void checkForMonthlyBalance(TreeMap<String, TreeMap<String, Double>> monthEndBalance, Transaction oneTransaction,
			Map<String, Double> monthlyMap, TreeMap<String, TreeMap<String, Double>> localMapForCalculation) {

		try {
			String source = oneTransaction.gettSource();
			TransactionType tType = oneTransaction.gettType();
			Double amountFromTransaction = oneTransaction.gettAmount();
			String yearMonthKey = oneTransaction.gettDateAndTime().getYear() + " " + oneTransaction.gettDateAndTime().getMonth(); 
			
			
			if()
			
			
			if(monthEndBalance.containsKey(source)) {
				TreeMap<String, Double> oneSourceAllMonthsMap = monthEndBalance.get(source);
				Double amount =0.0;
				if(oneSourceAllMonthsMap.containsKey(yearMonthKey)) {
					Double amountFromMonthMap = oneSourceAllMonthsMap.get(yearMonthKey);
					if(tType.equals(TransactionType.CREDIT)) 
						amount = amountFromMonthMap+amountFromTransaction;
					else 
						amount = amountFromMonthMap-amountFromTransaction;
					
				}else {
					//first entry for a month should be calculated wrt previous months entries as per debit or credit
					//amount=?
					
				}
				oneSourceAllMonthsMap.put(yearMonthKey, amount);
				
			}else {
				
			}
		}catch(Exception e) {
			logger.info("Exception in checkForMonthlyBalance: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * it is to sum all the credit amounts of all unique sources and destination
	 */
	private void sumAllCreditsOfEachSource() {
		
		Map<String, Double> allCredits = new TreeMap<>();
		try {
			
			transactionList
			.stream()
			.filter(oneTransaction -> (oneTransaction.gettType().equals(TransactionType.CREDIT)))
			.forEach(oneT -> {
				if(allCredits.containsKey(oneT.gettSource())) {
					double credits=allCredits.get(oneT.gettSource());
					credits+=oneT.gettAmount();
					allCredits.put(oneT.gettSource(), credits);
				}else {
					allCredits.put(oneT.gettSource(), oneT.gettAmount());
				}
			});
			
			for(Entry<String, Double> entry : allCredits.entrySet()) {
				System.out.println(entry.getKey() + "'s total credit amount is: " + df.format(entry.getValue()));
			}
			
				
		}catch(Exception e) {
			logger.info("Exception in sumAllCreditsOfEachSource: " + e);
			e.printStackTrace();
		}
	}
	
	public void landingFunction() {
		takeInput();
	}
	
	/**
	 * function to take input
	 */
	private void takeInput() {
		try {
			transactionList.add(new Transaction("T001",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34),"S1","S2",TransactionType.DEBIT));
			transactionList.add(new Transaction("T002",Math.random()*1000, LocalDateTime.of(2016, Month.JULY, 21, 11, 22, 23),"S1","S2",TransactionType.DEBIT));
			transactionList.add(new Transaction("T003",Math.random()*1000, LocalDateTime.of(2017, Month.AUGUST, 1, 10, 45, 12),"S1","S2",TransactionType.CREDIT));
			transactionList.add(new Transaction("T004",Math.random()*1000, LocalDateTime.of(2018, Month.AUGUST, 6, 16, 34, 45),"S1","S2",TransactionType.DEBIT));
			transactionList.add(new Transaction("T005",Math.random()*1000, LocalDateTime.of(2019, Month.JANUARY, 27, 21, 21, 12),"S1","S2",TransactionType.CREDIT));
			/*transactionList.add(new Transaction("T006",Math.random()*1000, LocalDateTime.of(2017, Month.FEBRUARY, 14, 18, 12, 34),"S1","S2",TransactionType.DEBIT));
			transactionList.add(new Transaction("T007",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 17, 13, 12, 13),"S1","S4",TransactionType.CREDIT));
			transactionList.add(new Transaction("T008",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 12, 13, 12, 13),"S1","S4",TransactionType.DEBIT));
			transactionList.add(new Transaction("T009",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 27, 13, 12, 13),"S1","S6",TransactionType.CREDIT));
			transactionList.add(new Transaction("T010",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 22, 13, 12, 13),"S1","S2",TransactionType.DEBIT));*/
			
			transactionList.add(new Transaction("T011",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34),"S2","S1",TransactionType.DEBIT));
			transactionList.add(new Transaction("T012",Math.random()*1000, LocalDateTime.of(2016, Month.AUGUST, 21, 11, 22, 23),"S2","S3",TransactionType.CREDIT));
			transactionList.add(new Transaction("T013",Math.random()*1000, LocalDateTime.of(2012, Month.JULY, 1, 10, 45, 12),"S2","S1",TransactionType.DEBIT));
			transactionList.add(new Transaction("T014",Math.random()*1000, LocalDateTime.of(2013, Month.AUGUST, 3, 23, 45, 12),"S2","S1",TransactionType.CREDIT));
			transactionList.add(new Transaction("T015",Math.random()*1000, LocalDateTime.of(2014, Month.MARCH, 1, 11, 45, 12),"S2","S3",TransactionType.DEBIT));
			/*transactionList.add(new Transaction("T016",Math.random()*1000, LocalDateTime.of(2018, Month.MARCH, 6, 11, 45, 12),"S2","S3",TransactionType.CREDIT));
			transactionList.add(new Transaction("T017",Math.random()*1000, LocalDateTime.of(2018, Month.OCTOBER, 16, 11, 45, 12),"S2","S3",TransactionType.DEBIT));
			transactionList.add(new Transaction("T018",Math.random()*1000, LocalDateTime.of(2018, Month.JULY, 25, 11, 45, 12),"S2","S4",TransactionType.CREDIT));
			transactionList.add(new Transaction("T019",Math.random()*1000, LocalDateTime.of(2018, Month.AUGUST, 24, 11, 45, 12),"S2","S6",TransactionType.DEBIT));
			transactionList.add(new Transaction("T020",Math.random()*1000, LocalDateTime.of(2021, Month.JANUARY, 10, 11, 45, 12),"S2","S6",TransactionType.CREDIT));*/
			
			
			Collections.sort(transactionList, new Comparator<Transaction>() {//Anonymous class created 
				//compare function overridden

				@Override
				public int compare(Transaction o1, Transaction o2) {
					Transaction transaction1 = (Transaction)o1;
					Transaction transaction2 = (Transaction)o2;
					
					int compare1 = transaction1.gettDateAndTime().compareTo(transaction2.gettDateAndTime());
					int compare2 = transaction1.gettSource().compareToIgnoreCase(transaction2.gettSource());
					if(compare1==0)
						return compare2;
					else
						return compare1;
				}
			});
			
			transactionList.forEach(trans -> System.out.println(trans.gettSource() + " || " + df.format(trans.gettAmount()) + " || " + trans.gettType() + " || " + trans.gettDateAndTime().getMonth() + " " + trans.gettDateAndTime().getYear()));
			
			System.out.println("Credit sum===================");
			sumAllCreditsOfEachSource();
			System.out.println("Month end balancesheet===================");
			getEachMonthBalance();
		}catch(Exception e) {
			logger.info("Exception while transaction input: " + e);
			e.printStackTrace();
		}
	}
	
}
