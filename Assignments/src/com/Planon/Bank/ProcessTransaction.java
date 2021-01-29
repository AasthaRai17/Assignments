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
	BankConstants bankConst = new BankConstants();
	// take input
	// sum all credits of all source/destination
	// get balance sheet for all source/destination

	/**
	 * Function will calculate the month end balance of all sources
	 */
	private void getEachMonthBalance() {
		try {
			// <S1,<Jan2011, 12568>>
			TreeMap<String, TreeMap<String, Double>> monthEndBalance = new TreeMap<>();
			// iterating through all the transactions
			transactionList.forEach(oneTransaction -> {
				checkForMonthlyBalance(monthEndBalance, oneTransaction);
			});

			// printing the details
			Map<String, Double> monthMap = new HashMap<>();
			for (Entry<String, TreeMap<String, Double>> entry : monthEndBalance.entrySet()) {
				System.out.println(entry.getKey() + ": ");
				monthMap = monthEndBalance.get(entry.getKey());

				for (Entry<String, Double> entry1 : monthMap.entrySet()) {
					System.out.println(entry1.getKey() + "::::: " + df.format(entry1.getValue()));
				}
			}

		} catch (Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
	}

	
	
	/**
	 * function to calculate monthly balancesheet
	 * @param monthEndBalance is a map for the balances <Source1, <July 2018, 6000>>
	 * @param oneTransaction is one transaction form the list of transactions
	 */
	private void checkForMonthlyBalance(TreeMap<String, TreeMap<String, Double>> monthEndBalance, Transaction oneTransaction) {

		try {
			String source = oneTransaction.getCustomerId();
			TransactionType tType = oneTransaction.gettType();
			Double amountFromTransaction = oneTransaction.gettAmount();
			String yearMonthKey = oneTransaction.gettDateAndTime().getYear() + " "
					+ oneTransaction.gettDateAndTime().getMonth();
			//2018 July
			Double amount;

			//if my map already has the source then getting value from the map
			if (monthEndBalance.containsKey(source)) {
				TreeMap<String, Double> oneSourceAllMonthsMap = monthEndBalance.get(source);

				if (oneSourceAllMonthsMap.containsKey(yearMonthKey)) {
					Double amountFromMonthMap = oneSourceAllMonthsMap.get(yearMonthKey);
					
					amount = tType.equals(TransactionType.CREDIT) ? 
						amountFromMonthMap + amountFromTransaction : 
						amountFromMonthMap - amountFromTransaction;

				} else {//if a new month entry is being added
					// first entry for a month should be calculated wrt previous months entries as
					// per debit or credit so using the last entry's amount value as the map is sorted
					// amount=?
					amount = tType.equals(TransactionType.CREDIT) ? 
							amountFromTransaction + oneSourceAllMonthsMap.lastEntry().getValue() : 
							oneSourceAllMonthsMap.lastEntry().getValue() - amountFromTransaction;
				}
				oneSourceAllMonthsMap.put(yearMonthKey, amount);

			} else {//if map doesnt has the source adding a new map with the source
				amount = tType.equals(TransactionType.CREDIT) ? 
						amountFromTransaction : 
						-amountFromTransaction;
				
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
	 * it is to sum all the credit amounts of all unique sources and destination
	 */
	private void sumAllCreditsOfEachSource() {

		Map<String, Double> allCredits = new TreeMap<>();
		try {

			transactionList.stream()
					.filter(oneTransaction -> (oneTransaction.gettType().equals(TransactionType.CREDIT)))
					.forEach(oneT -> {
						if (allCredits.containsKey(oneT.getCustomerId())) {
							allCredits.put(oneT.getCustomerId(), (allCredits.get(oneT.getCustomerId())+oneT.gettAmount()));
						} else {
							allCredits.put(oneT.getCustomerId(), oneT.gettAmount());
						}
					});

			for (Entry<String, Double> entry : allCredits.entrySet()) {
				System.out.println(entry.getKey() + "'s total credit amount is: " + df.format(entry.getValue()));
			}

		} catch (Exception e) {
			logger.info("Exception in sumAllCreditsOfEachSource: " + e);
			e.printStackTrace();
		}
	}

	public void initiateTheBankTransactionProcess() {
		takeInput();
		System.out.println("Credit sum===================");
		sumAllCreditsOfEachSource();
		System.out.println("Month end balancesheet===================");
		getEachMonthBalance();
	}

	/**
	 * function to take input
	 */
	private void takeInput() {
		try {
			transactionList.add(new Transaction("T001", Math.random() * 1000,
					LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34), "S1", "S2", TransactionType.DEBIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T002", Math.random() * 1000,
					LocalDateTime.of(2016, Month.JULY, 21, 11, 22, 23), "S1", "S2", TransactionType.DEBIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T003", Math.random() * 1000,
					LocalDateTime.of(2017, Month.AUGUST, 1, 10, 45, 12), "S1", "S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T004", Math.random() * 1000,
					LocalDateTime.of(2018, Month.AUGUST, 6, 16, 34, 45), "S1", "S2", TransactionType.DEBIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T005", Math.random() * 1000,
					LocalDateTime.of(2019, Month.JANUARY, 27, 21, 21, 12), "S1", "S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			
			transactionList.add(new Transaction("T006", Math.random() * 1000,
					LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34), "S2", "S1", TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T007", Math.random() * 1000,
					LocalDateTime.of(2016, Month.JULY, 21, 11, 22, 23), "S2", "S1", TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T008", Math.random() * 1000,
					LocalDateTime.of(2017, Month.AUGUST, 1, 10, 45, 12), "S2", "S1", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T009", Math.random() * 1000,
					LocalDateTime.of(2018, Month.AUGUST, 6, 16, 34, 45), "S2", "S1", TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T010", Math.random() * 1000,
					LocalDateTime.of(2019, Month.JANUARY, 27, 21, 21, 12), "S2", "S1", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));

			transactionList.add(new Transaction("T011", Math.random() * 1000,
					LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34), "S2", "S1", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T012", Math.random() * 1000,
					LocalDateTime.of(2016, Month.AUGUST, 21, 11, 22, 23), "S2", "S3", TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T013", Math.random() * 1000,
					LocalDateTime.of(2012, Month.JULY, 1, 10, 45, 12), "S2", "S1", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T014", Math.random() * 1000,
					LocalDateTime.of(2013, Month.AUGUST, 3, 23, 45, 12), "S2", "S1", TransactionType.CREDIT, bankConst.CURRENT_ACCOUNT));
			transactionList.add(new Transaction("T015", Math.random() * 1000,
					LocalDateTime.of(2014, Month.MARCH, 1, 11, 45, 12), "S2", "S3", TransactionType.DEBIT, bankConst.CURRENT_ACCOUNT));
			
			transactionList.add(new Transaction("T016", Math.random() * 1000,
					LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34), "S1", "S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T017", Math.random() * 1000,
					LocalDateTime.of(2016, Month.AUGUST, 21, 11, 22, 23), "S3", "S2", TransactionType.DEBIT, bankConst.FIXED_ACCOUNT));
			transactionList.add(new Transaction("T018", Math.random() * 1000,
					LocalDateTime.of(2012, Month.JULY, 1, 10, 45, 12), "S1", "S2", TransactionType.CREDIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T019", Math.random() * 1000,
					LocalDateTime.of(2013, Month.AUGUST, 3, 23, 45, 12), "S1", "S2", TransactionType.DEBIT, bankConst.SAVING_ACCOUNT));
			transactionList.add(new Transaction("T020", Math.random() * 1000,
					LocalDateTime.of(2014, Month.MARCH, 1, 11, 45, 12), "S3", "S2", TransactionType.CREDIT, bankConst.FIXED_ACCOUNT));

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

		} catch (Exception e) {
			logger.info("Exception while transaction input: " + e);
			e.printStackTrace();
		}
	}

}
