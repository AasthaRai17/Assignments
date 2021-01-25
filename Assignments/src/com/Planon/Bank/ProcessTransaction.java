package com.Planon.Bank;

import java.util.List;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProcessTransaction {

	static List<Transaction> transactionList = new ArrayList<>();
	static Logger logger = Logger.getLogger(ProcessTransaction.class.getName());
	//take input
	//sum all credits of all source/destination
	//get balance sheet for all source/destination
	
	
	private void getEachDayBalance() {
		try {
			
		}catch(Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
	}
	
	
	
	private void sumAllCreditsOfEachSource() {
		
		try {
			transactionList
				.stream()
				.filter(oneTransaction -> oneTransaction.gettType().equals(TransactionType.CREDIT))
				.
		}catch(Exception e) {
			logger.info("Exception in sumAllCreditsOfEachSource: " + e);
			e.printStackTrace();
		}
	}
	
	private void takeInput() {
		try {
			transactionList.add(new Transaction("T001",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34),"S1","S2",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T002",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 21, 11, 22, 23),"S1","S2",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T003",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 1, 10, 45, 12),"S1","S2",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T004",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 6, 16, 34, 45),"S1","S2",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T005",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 27, 21, 21, 12),"S1","S2",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T006",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 14, 18, 12, 34),"S1","S2",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T007",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 17, 13, 12, 13),"S1","S4",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T008",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 12, 13, 12, 13),"S1","S4",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T009",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 27, 13, 12, 13),"S1","S6",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T010",Math.random()*1000, LocalDateTime.of(2020, Month.SEPTEMBER, 22, 13, 12, 13),"S1","S2",TransactionType.DEBIT,0));
			
			transactionList.add(new Transaction("T011",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 34),"S2","S1",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T012",Math.random()*1000, LocalDateTime.of(2015, Month.JULY, 21, 11, 22, 23),"S2","S3",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T013",Math.random()*1000, LocalDateTime.of(2012, Month.JULY, 1, 10, 45, 12),"S2","S1",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T014",Math.random()*1000, LocalDateTime.of(2012, Month.JULY, 3, 23, 45, 12),"S2","S1",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T015",Math.random()*1000, LocalDateTime.of(2018, Month.MARCH, 1, 11, 45, 12),"S2","S3",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T016",Math.random()*1000, LocalDateTime.of(2018, Month.MARCH, 6, 11, 45, 12),"S2","S3",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T017",Math.random()*1000, LocalDateTime.of(2018, Month.OCTOBER, 16, 11, 45, 12),"S2","S3",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T018",Math.random()*1000, LocalDateTime.of(2018, Month.JULY, 25, 11, 45, 12),"S2","S4",TransactionType.CREDIT,0));
			transactionList.add(new Transaction("T019",Math.random()*1000, LocalDateTime.of(2018, Month.AUGUST, 24, 11, 45, 12),"S2","S6",TransactionType.DEBIT,0));
			transactionList.add(new Transaction("T020",Math.random()*1000, LocalDateTime.of(2021, Month.JANUARY, 10, 11, 45, 12),"S2","S6",TransactionType.CREDIT,0));
			
			Collections.sort(transactionList, new Comparator<Transaction>() {

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
			
		}catch(Exception e) {
			logger.info("Exception while transaction input: " + e);
			e.printStackTrace();
		}
	}
	
}
