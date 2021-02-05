package com.Planon.Bank;

public class AccountTypeFactory {
	
	public static AccountType getAccountType(String accountType){
		if(BankConstants.CURRENT_ACCOUNT.equalsIgnoreCase(accountType))
			return new CurrentAccount();
		else if(BankConstants.SAVING_ACCOUNT.equalsIgnoreCase(accountType))
			return new SavingAccount();
		else if(BankConstants.FIXED_ACCOUNT.equalsIgnoreCase(accountType))
			return new FixedAccount();
		else 
			return null;
	}
}
