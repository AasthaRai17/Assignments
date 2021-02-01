package com.Planon.Bank;

public class CurrentAccount implements AccountType {

	@Override
	public Integer getInterestRate() {
		return BankConstants.INTEREST_RATES.get(BankConstants.CURRENT_ACCOUNT);
	}

}
