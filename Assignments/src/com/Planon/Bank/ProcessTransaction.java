package com.Planon.Bank;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.logging.Logger;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class ProcessTransaction implements BankFunctions {

	static List<Transaction> transactionList = new ArrayList<>();
	static Logger logger = Logger.getLogger(ProcessTransaction.class.getName());
	private static DecimalFormat df = new DecimalFormat("0.00");
	BankConstants bankConst = new BankConstants();

	public void initiateTheBankTransactionProcess() {

		takeInput();
		sortTheInputData();

		System.out.println("Credit sum===================");
		getSumOfAllCredits();

		System.out.println("Month end balancesheet===================");
		getEachMonthBalance();
		 
		System.out.println("Interest/Quarter===================");
		getQuaterlyInteresetOfAnAccount();
		
		System.out.println("Each Day Balance map==============");
		getEachDayBalance();
	}

	/**
	 * function to print sum of all credit of all sources
	 */
	private void getSumOfAllCredits() {

		try {
			Map<String, Double> sumOfAmountOfATransactionType = sumAllCreditsOrDebitsOfEachSource(
					TransactionType.CREDIT);
			for (Entry<String, Double> entry : sumOfAmountOfATransactionType.entrySet()) {
				System.out.println(entry.getKey() + "'s total " + TransactionType.CREDIT + " amount is: "
						+ df.format(entry.getValue()));
			}
		} catch (Exception e) {
			logger.info("Exception in getSumOfAllCredits: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * it is to sum all the credit/debit amounts of all unique sources
	 * 
	 * @param debitOrCredit can be Debit or Credit, accordingly the sum will be done
	 */
	public Map<String, Double> sumAllCreditsOrDebitsOfEachSource(TransactionType debitOrCredit) {

		Map<String, Double> sumOfAmountOfATransactionType = new TreeMap<>();

		try {

			transactionList.stream().filter(oneTransaction -> (oneTransaction.gettType().equals(debitOrCredit)))
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
	 * Function will calculate the month end balance of all sources
	 */
	public void getEachMonthBalance() {
		try {
			// <S1,<Jan2011, 12568>>
			TreeMap<String, TreeMap<String, Double>> monthEndBalance = new TreeMap<>();
			// iterating through all the transactions
			transactionList.forEach(oneTransaction -> {
				checkForMonthlyBalance(monthEndBalance, oneTransaction);
			});

			printMonthEndBalanceSheet(monthEndBalance);

		} catch (Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * function to just the print the month end balance sheet
	 * 
	 * @param monthEndBalance
	 */
	private void printMonthEndBalanceSheet(TreeMap<String, TreeMap<String, Double>> monthEndBalance) {
		// printing the details
		Map<String, Double> monthMap = new HashMap<>();
		for (Entry<String, TreeMap<String, Double>> entry : monthEndBalance.entrySet()) {
			System.out.println(entry.getKey() + ": ");
			monthMap = monthEndBalance.get(entry.getKey());

			for (Entry<String, Double> entry1 : monthMap.entrySet()) {
				System.out.println(entry1.getKey() + "::::: " + df.format(entry1.getValue()));
			}
		}
	}

	/**
	 * function to calculate monthly balancesheet
	 * 
	 * @param monthEndBalance is a map for the balances <Source1, <July 2018, 6000>>
	 * @param oneTransaction  is one transaction form the list of transactions
	 */
	private void checkForMonthlyBalance(TreeMap<String, TreeMap<String, Double>> monthEndBalance,
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

	@Override
	public void getQuaterlyInteresetOfAnAccount() {
		TreeMap<String, Double> quarterlyBalanceMap = getQuaterlyBalanceData();
		getInterestRateAndCalculateBalance(quarterlyBalanceMap);
	}
	
	/**
	 * creating quarter based map with balance for each source
	 * @return
	 */
	public TreeMap<String, Double> getQuaterlyBalanceData(){
		
		TreeMap<String, Double> quarterlyBalanceMap = new TreeMap<>();
		try {
			
			transactionList.forEach(oneTransaction -> {
				
				String quarter = bankConst.MONTH_QUARTER_MAP.get(oneTransaction.gettDateAndTime().getMonth().toString());
						
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
	 */
	public void getInterestRateAndCalculateBalance(TreeMap<String, Double> quarterlyBalanceMap) {
		TreeMap<String, Double> avgQuarterBalanceMap = new TreeMap<>();
		try {
				quarterlyBalanceMap.forEach((quarterKey, balance) -> {
				int numberOfDays=0;
				if(checkLeapYear(Integer.parseInt(quarterKey.split(" ")[2])))
					numberOfDays= bankConst.QUARTER_DAYS_MAP_LEAP.get(quarterKey.split(" ")[1]);
				else
					numberOfDays= bankConst.QUARTER_DAYS_MAP_NON_LEAP.get(quarterKey.split(" ")[1]);
				
				AccountType accountType = AccountTypeFactory.getAccountType(quarterKey.split(" ")[3] + " " + quarterKey.split(" ")[4]);
				String quarterSourceKey = quarterKey.split(" ")[2] 
						+ " " + quarterKey.split(" ")[1] 
						+ " " + quarterKey.split(" ")[0];
				avgQuarterBalanceMap.put(quarterSourceKey, 
						BankFunctions.calculateInterest((balance/numberOfDays), 
								accountType.getInterestRate()));
			});
				System.out.println(avgQuarterBalanceMap);
		}catch(Exception e) {
			logger.info("Exception in checkForMonthlyBalance: " + e);
			e.printStackTrace();
		}
	
	}

	boolean checkLeapYear(int year) {
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
	}

	
	/**
	 * @return eachDayBalance of structure <S1,<2012,<January, <18, 12000>>>>
	 */
	private TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> getEachDayBalance() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance = new TreeMap<>();
		try {
			// calculating each day's balance
			// <S1,<2012,<January, <18, 12000>>>> format data in eachDayBalance
			transactionList.forEach(oneTransaction -> {

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
		System.out.println(eachDayBalance);
		return eachDayBalance;
	}
	
	/**
	 * function to take input
	 */
	private void takeInput() {
		try {
			transactionList.add(new Transaction("T001", 1000000, LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T002", 8000000, LocalDateTime.of(2015, Month.JULY, 15, 11, 22, 23), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T003", 456, LocalDateTime.of(2015, Month.JULY, 29, 10, 45, 12), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T004", 234, LocalDateTime.of(2018, Month.AUGUST, 6, 16, 34, 45), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T005", 678, LocalDateTime.of(2019, Month.JANUARY, 27, 21, 21, 12),
					"S1", "S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));

			/*
			 * transactionList.add(new Transaction("T006", 100, LocalDateTime.of(2015,
			 * Month.JULY, 29, 19, 30, 34), "S2", "S1", TransactionType.CREDIT,
			 * bankConst.CURRENT_ACCOUNT)); transactionList.add(new Transaction("T007", 200,
			 * LocalDateTime.of(2015, Month.JULY, 15, 11, 22, 23), "S2", "S1",
			 * TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT)); transactionList.add(new
			 * Transaction("T008", 456, LocalDateTime.of(2015, Month.JULY, 29, 10, 45, 12),
			 * "S2", "S1", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			 * transactionList.add(new Transaction("T009", 234, LocalDateTime.of(2018,
			 * Month.AUGUST, 6, 16, 34, 45), "S2", "S1", TransactionType.CREDIT,
			 * bankConst.CURRENT_ACCOUNT)); transactionList.add(new Transaction("T010", 678,
			 * LocalDateTime.of(2019, Month.JANUARY, 27, 21, 21, 12), "S2", "S1",
			 * TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			 */

			/*
			 * transactionList.add(new Transaction("T011", 600, LocalDateTime.of(2015,
			 * Month.JULY, 29, 19, 30, 34), "S2", "S1", TransactionType.DEBIT,
			 * bankConst.CURRENT_ACCOUNT)); transactionList.add(new Transaction("T012", 456,
			 * LocalDateTime.of(2016, Month.AUGUST, 21, 11, 22, 23), "S2", "S3",
			 * TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT)); transactionList.add(new
			 * Transaction("T013", 890, LocalDateTime.of(2012, Month.JULY, 1, 10, 45, 12),
			 * "S2", "S1", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			 * transactionList.add(new Transaction("T014", 234, LocalDateTime.of(2013,
			 * Month.AUGUST, 3, 23, 45, 12), "S2", "S1", TransactionType.CREDIT,
			 * bankConst.CURRENT_ACCOUNT)); transactionList.add(new Transaction("T015", 435,
			 * LocalDateTime.of(2014, Month.MARCH, 1, 11, 45, 12), "S2", "S3",
			 * TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			 */

			transactionList.add(new Transaction("T016", 600, LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			/*
			 * transactionList.add(new Transaction("T017", 456, LocalDateTime.of(2016,
			 * Month.AUGUST, 21, 11, 22, 23), "S3", "S2", TransactionType.DEBIT,
			 * bankConst.FIXED_ACCOUNT));
			 */
			transactionList.add(new Transaction("T018", 890, LocalDateTime.of(2012, Month.JULY, 1, 10, 45, 12), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T019", 234, LocalDateTime.of(2013, Month.AUGUST, 3, 23, 45, 12), "S1",
					"S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			/*
			 * transactionList.add(new Transaction("T020", 435, LocalDateTime.of(2014,
			 * Month.MARCH, 1, 11, 45, 12), "S3", "S2", TransactionType.CREDIT,
			 * bankConst.FIXED_ACCOUNT));
			 */

		} catch (Exception e) {
			logger.info("Exception while transaction input: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * Sorting the input on the basis of date and then source id
	 */
	private void sortTheInputData() {
		Collections.sort(transactionList, new Comparator<Transaction>() {// Anonymous class created
			// compare function overridden

			@Override
			public int compare(Transaction o1, Transaction o2) {
				Transaction transaction1 = (Transaction) o1;
				Transaction transaction2 = (Transaction) o2;

				int compare1 = transaction1.gettDateAndTime().compareTo(transaction2.gettDateAndTime());
				int compare2 = transaction1.gettSource().compareToIgnoreCase(transaction2.gettSource());
				if (compare1 == 0)
					return compare2;
				else
					return compare1;
			}
		});

		transactionList.forEach(trans -> System.out
				.println(trans.gettSource() + " || " + df.format(trans.gettAmount()) + " || " + trans.gettType()
						+ " || " + trans.gettDateAndTime().getMonth() + " " + trans.gettDateAndTime().getYear()));
	}


}
