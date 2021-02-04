package com.Planon.Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.Planon.GenericUtilityFunctions.ValidationFunctions;

public class BankProcess {
	
	public static void main(String[] args) {
		ProcessTransaction processTransaction = new ProcessTransaction();
		//bankProcess.initiateTheBankTransactionProcess();
		processTransaction.takeInput();
		processTransaction.sortTheInputData();

		System.out.println("Credit sum===================");
		processTransaction.getSumOfAllCredits();

		System.out.println("Month end balancesheet===================");
		processTransaction.getEachMonthBalanceSheet();
		 
		System.out.println("Interest/Quarter===================");
		processTransaction.getQuaterlyInterestAndPrint();
		
		System.out.println("Each Day Balance map==============");
		processTransaction.getEachDayBalanceAndPrint();
		
		System.out.println("Get Q2 Quaterly data for year 2015 for Source S1================");
		HashMap<String, Object> identifierMap = new HashMap<>();
		identifierMap.put("GetData","Quaterly");
		identifierMap.put("TimePeriod","Q2");
		identifierMap.put("SourceId","S1");
		identifierMap.put("Year","2015");
		Object balanceMapForSpecificPeriod = processTransaction.getAndPrintDataForAQuarter(identifierMap);
		
	}
}
