package com.planon.facilities;

/**
 * class can have multiple functions related to stationary management which can be even a new module
 * to manage the inventory or so.
 * @author aarai
 *
 */
public interface StationeryManagement{

	//Logger logger = Logger.getLogger(StationeryManagement.class.getName());
	//a list of stationary item can be maintained 
	//every time when some item is given to employee the inventory should be updated
	//inventory update should be addition as well as subtraction of items
	
	//public void addStationeryItem();
	
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void assignTheStationeryToUser(String ticketDesciption);
	
	/*
	 * public default void getStationeryInventoryDetails() {
	 * logger.info("stationary inventory...."); }
	 */
	
}
