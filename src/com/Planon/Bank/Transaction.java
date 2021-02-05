package com.Planon.Bank;

import java.time.LocalDateTime;

enum TransactionType{
	CREDIT,DEBIT;
}

public class Transaction extends BankAccount{
	
	String tId;
	LocalDateTime tDateAndTime;
	String tSource;
	String tDestination;
	TransactionType tType;
	double tAmount;
	
	public Transaction(String tId, double tAmount, LocalDateTime tDateAndTime, String tSource, String tDestination,
			TransactionType tType, String accountType) {
		super();
		this.tId = tId;
		this.tAmount = tAmount;
		this.tDateAndTime = tDateAndTime;
		this.tSource = tSource;
		this.tDestination = tDestination;
		this.tType = tType;
		super.customerId=tSource;
		super.accountType=accountType;
	}

	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public double gettAmount() {
		return tAmount;
	}
	public void settAmount(double tAmount) {
		this.tAmount = tAmount;
	}
	public LocalDateTime gettDateAndTime() {
		return tDateAndTime;
	}
	public void settDateAndTime(LocalDateTime tDateAndTime) {
		this.tDateAndTime = tDateAndTime;
	}
	public String gettSource() {
		return tSource;
	}
	public void settSource(String tSource) {
		this.tSource = tSource;
	}
	public String gettDestination() {
		return tDestination;
	}
	public void settDestination(String tDestination) {
		this.tDestination = tDestination;
	}
	public TransactionType gettType() {
		return tType;
	}
	public void settType(TransactionType tType) {
		this.tType = tType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(tAmount);
		result  = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tDateAndTime == null) ? 0 : tDateAndTime.hashCode());
		result = prime * result + ((tDestination == null) ? 0 : tDestination.hashCode());
		result = prime * result + ((tId == null) ? 0 : tId.hashCode());
		result = prime * result + ((tSource == null) ? 0 : tSource.hashCode());
		result = prime * result + ((tType == null) ? 0 : tType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(tAmount) != Double.doubleToLongBits(other.tAmount))
			return false;
		if (tDateAndTime == null) {
			if (other.tDateAndTime != null)
				return false;
		} else if (!tDateAndTime.equals(other.tDateAndTime))
			return false;
		if (tDestination == null) {
			if (other.tDestination != null)
				return false;
		} else if (!tDestination.equals(other.tDestination))
			return false;
		if (tId == null) {
			if (other.tId != null)
				return false;
		} else if (!tId.equals(other.tId))
			return false;
		if (tSource == null) {
			if (other.tSource != null)
				return false;
		} else if (!tSource.equals(other.tSource))
			return false;
		if (tType != other.tType)
			return false;
		return true;
	}

	/*
	 * @Override public int compareTo(Object toBeCompared) { Transaction
	 * toBeComparedTrasaction = (Transaction)toBeCompared; return
	 * toBeComparedTrasaction.gettDateAndTime().compareTo(this.tDateAndTime); }
	 */

	/*
	 * @Override public int compare(Object compareObj1, Object compareObj2) {
	 * 
	 * Transaction transaction1 = (Transaction)compareObj1; Transaction transaction2
	 * = (Transaction)compareObj2;
	 * 
	 * int compare1 =
	 * transaction1.gettDateAndTime().compareTo(transaction2.gettDateAndTime()); int
	 * compare2 =
	 * transaction1.gettSource().compareToIgnoreCase(transaction2.gettSource());
	 * if(compare1==0) return compare2; else return compare1; }
	 */

	/*
	 * @Override public boolean equals(Object toCheck) { if(this == toCheck) return
	 * true;
	 * 
	 * if(!(toCheck instanceof Transaction)) return false;
	 * 
	 * Transaction oneTransaction = (Transaction)toCheck; if(Double.compare(tAmount,
	 * oneTransaction.tAmount) == 0 &&
	 * tDateAndTime.equals(oneTransaction.tDateAndTime) &&
	 * tSource.equalsIgnoreCase(oneTransaction.tSource) &&
	 * tDestination.equalsIgnoreCase(oneTransaction.tDestination) &&
	 * tType.equals(oneTransaction.tType) &&
	 * tId.equalsIgnoreCase(oneTransaction.tId)) return false;
	 * 
	 * return true; }
	 */
	
	
}

