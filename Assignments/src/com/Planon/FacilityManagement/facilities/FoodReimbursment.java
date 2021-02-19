package com.planon.facilities;

/**
 * as the facility can have n number of functions and even a separate module to handle the finances
 * this interface can be used for that purpose 
 * @author aarai
 *
 */
public interface FoodReimbursment extends ReimbursmentManagement {
	
	/**
	 * calculate the amount to be reimbursed based on the maximum amount to be reimbursed
	 * by the company
	 */
	public static void calculateAmountToBeReimbursedForFood() {
	}
	
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void initiateTheFoodReimbursementProcess(String ticketDesciption);
}
