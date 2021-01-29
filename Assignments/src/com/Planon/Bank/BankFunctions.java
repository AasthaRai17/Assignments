package com.Planon.Bank;

import java.util.logging.Logger;

import com.Planon.Library.LibraryProcesses;

//factory design pattern
public interface BankFunctions {
	void sumAllCreditsOfEachSource();
	void getEachMonthBalance();
	void getInteresetOfAnAccount();
	BankConstants constants = new BankConstants();
	
	static double calculateInterest(double principalAmount, String AccountType) {
		return principalAmount * (constants.INTEREST_RATES.get(AccountType)/100);
	}
}
