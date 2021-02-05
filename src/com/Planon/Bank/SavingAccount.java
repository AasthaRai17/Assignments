package com.Planon.Bank;

public class SavingAccount implements AccountType {

	@Override
	public Float getInterestRate() {
		return BankConstants.INTEREST_RATES.get(BankConstants.SAVING_ACCOUNT);
	}

}
