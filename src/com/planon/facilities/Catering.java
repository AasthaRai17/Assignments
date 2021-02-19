package com.planon.facilities;
/**
 * class will have all types of functions which can be there related to catering type of facility
 * which can be a separate module for handling catering services, food menu, quality checks etc
 * @author aarai
 *
 */
public interface Catering extends Facility {
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void checkTheCateringServiceIssue(String ticketDesciption);
}
