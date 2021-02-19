 package com.planon.facilities;

 /**
  * the interface can have multiple functions to handle the security of an organization
  * which can be camera installation or even a separate module to handle the videos and security machines
  * @author aarai
  *
  */
public interface Security extends Facility {
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void checkTheSecurityIssue(String ticketDesciption);
}
