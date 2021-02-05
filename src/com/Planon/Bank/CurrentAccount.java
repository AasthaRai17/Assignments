package com.Planon.Bank;

public class CurrentAccount implements AccountType {

	@Override
	public Float getInterestRate() {
		return BankConstants.INTEREST_RATES.get(BankConstants.CURRENT_ACCOUNT);
	}

}
