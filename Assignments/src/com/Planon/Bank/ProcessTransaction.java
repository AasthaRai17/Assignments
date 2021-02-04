package com.Planon.Bank;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.json.simple.*;

import com.Planon.GenericUtilityFunctions.ValidationFunctions;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ProcessTransaction{

	public static List<Transaction> transactionList = new ArrayList<>();
	static Logger logger = Logger.getLogger(ProcessTransaction.class.getName());
	private static final DecimalFormat df = new DecimalFormat("0.00");
	public static final BankConstants bankConst = new BankConstants();
	public static final ValidationFunctions utilityFunction = new ValidationFunctions();
	BankFunctions bankFunctionImpl = new BankFunctionsImplementation();

	/**
	 * function to print sum of all credit of all sources
	 */
	protected void getSumOfAllCredits() {

		try {
			Map<String, Double> sumOfAmountOfATransactionType = bankFunctionImpl.sumAllCreditsOrDebitsOfEachSource(
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
	 * Function will calculate the month end balance of all sources
	 */
	public void getEachMonthBalanceSheet() {
		try {
			// <S1,<Jan2011, 12568>>
			 TreeMap<String, TreeMap<String, Double>> monthEndBalance = bankFunctionImpl.getEachMonthBalance();
			
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
	protected void printMonthEndBalanceSheet(TreeMap<String, TreeMap<String, Double>> monthEndBalance) {
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


	public void getQuaterlyInterestAndPrint() {
		TreeMap<String, Double> avgQuarterBalanceMap = bankFunctionImpl.getQuaterlyInterestOfAnAccount();
		printQuarterInterestMap(avgQuarterBalanceMap);
	}
	

	private void printQuarterInterestMap(TreeMap<String, Double> avgQuarterBalanceMap) {
		try {
			avgQuarterBalanceMap.entrySet().forEach(oneEntry -> {
				System.out.println(oneEntry.getKey().split(" ")[2] 
						+ "'s "
						+ oneEntry.getKey().split(" ")[1] 
						+ " "
						+ oneEntry.getKey().split(" ")[0]
						+ " "
						+ " interest is : "
						+ df.format(oneEntry.getValue()));
			});
		}catch(Exception e) {
			logger.info("Exception in printQuarterInterestMap: " + e);
			e.printStackTrace();
		}
	}

	
	/**
	 * function to take input
	 */
	protected void takeInput() {
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
	protected void sortTheInputData() {
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

	public Object getAndPrintDataForAQuarter(HashMap<String, Object> identifierMap) {
		
		Object balanceMapForSpecificPeriod = new Object();
		try {
			 balanceMapForSpecificPeriod = bankFunctionImpl.getDataForASpecificPeriod(identifierMap);
			 List<TreeMap<String, TreeMap<String, Double>>> quarterData = (List<TreeMap<String, TreeMap<String, Double>>>)(balanceMapForSpecificPeriod);	 
			 //((Iterable<TreeMap<String, TreeMap<String, Double>>>) balanceMapForSpecificPeriod).forEach(System.out::println);
		}catch(Exception e) {
			logger.info("Exception while transaction input: " + e);
			e.printStackTrace();
		}
		return balanceMapForSpecificPeriod;
	}


	public void getQuaterlyInterest() {
		bankFunctionImpl.getQuaterlyInterestOfAnAccount();
	}


	public void getEachDayBalanceAndPrint() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance 
		= bankFunctionImpl.getEachDayBalance();
		System.out.println(eachDayBalance);
	}


}
