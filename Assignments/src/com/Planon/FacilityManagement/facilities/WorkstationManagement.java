package com.planon.facilities;

/**
 * as there can be n number of workstation related functions, the interface can have the functions
 * @author aarai
 *
 */
public interface WorkstationManagement{
	
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void checkTheWorkstationIssue(String ticketDesciption);
}
