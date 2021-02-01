package com.Planon.Bank;

public class FixedAccount implements AccountType {

	@Override
	public Integer getInterestRate() {
		return BankConstants.INTEREST_RATES.get(BankConstants.FIXED_ACCOUNT);
	}

}
