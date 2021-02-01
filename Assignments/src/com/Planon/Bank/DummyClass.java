package com.Planon.Bank;

import java.util.TreeMap;

public class DummyClass {
	public void getQuaterlyInteresetOfAnAccount() {
		try {
			TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance = getEachDayBalance();
			TreeMap<String, TreeMap<String, Double>> sourceQuarterBalanceMap = new TreeMap<>();
			
			eachDayBalance.entrySet().forEach(oneEntrySet -> {
				if(sourceQuarterBalanceMap.containsKey(oneEntrySet.getKey())) {
					
				}
			});
		}catch(Exception e) {
			logger.info("Exception in getQuaterlyInteresetOfAnAccount: " + e);
			e.printStackTrace();
		}
		
	}

	/**
	 * @return eachDayBalance of structure <S1,<2012,<January, <18, 12000>>>>
	 */
	private TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> getEachDayBalance() {
		TreeMap<String, TreeMap<String, TreeMap<String, TreeMap<String, Double>>>> eachDayBalance = new TreeMap<>();
		try {
			// calculating each day's balance
			// <S1,<2012,<January, <18, 12000>>>> format data in eachDayBalance
			transactionList.forEach(oneTransaction -> {

				String year = String.valueOf(oneTransaction.gettDateAndTime().getYear());
				String month = oneTransaction.gettDateAndTime().getMonth().toString();
				String date = String.valueOf(oneTransaction.gettDateAndTime().getDayOfMonth());
				String sourcId = oneTransaction.gettSource();
				double amount = (oneTransaction.gettType().equals(TransactionType.CREDIT)) ? oneTransaction.gettAmount()
						: -oneTransaction.gettAmount();

				if (eachDayBalance.containsKey(sourcId)) {
					if (eachDayBalance.get(sourcId).containsKey(year)) {
						if (eachDayBalance.get(sourcId).get(year).containsKey(month)) {
							if (eachDayBalance.get(sourcId).get(year).get(month).containsKey(date))
								amount += eachDayBalance.get(sourcId).get(year).get(month).get(date);
							
							eachDayBalance.get(sourcId).get(year).get(month).put(date, amount);
						} else {
							TreeMap<String, Double> tempMap1 = new TreeMap<>();
							tempMap1.put(date, amount);
							eachDayBalance.get(sourcId).get(year).put(month, tempMap1);
						}
					} else {
						TreeMap<String, TreeMap<String, Double>> tempMap = new TreeMap<>();
						TreeMap<String, Double> tempMap1 = new TreeMap<>();
						tempMap1.put(date, amount);
						tempMap.put(month, tempMap1);
						eachDayBalance.get(sourcId).put(year, tempMap);
					}
				} else {
					TreeMap<String, TreeMap<String, TreeMap<String, Double>>> tempMap2 = new TreeMap<>();
					TreeMap<String, TreeMap<String, Double>> tempMap = new TreeMap<>();
					TreeMap<String, Double> tempMap1 = new TreeMap<>();
					tempMap1.put(date, amount);
					tempMap.put(month, tempMap1);
					tempMap2.put(year, tempMap);
					eachDayBalance.put(sourcId, tempMap2);
				}
			
			});
			
			
		} catch (Exception e) {
			logger.info("Exception in getEachDayBalance: " + e);
			e.printStackTrace();
		}
		return eachDayBalance;
	}
}
