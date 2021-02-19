package com.planon.facilities;

/**
 * class can have n number of functions which can be to manage the products required in cleaning or 
 * sanitization etc
 * @author aarai
 *
 */
public interface Cleaning extends Facility {
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void doTheCleaning(String ticketDesciption);
}
