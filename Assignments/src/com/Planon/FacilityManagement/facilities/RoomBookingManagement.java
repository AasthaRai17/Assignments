package com.planon.facilities;

/**
 * the interface can have multiple functions to book a room or manage the availability of rooms
 * @author aarai
 *
 */
public interface RoomBookingManagement{
	//ArrayList<Integer> listOfAvailableRooms = new ArrayList<>();
	/**
	 * its a general function which will be to do the task as per the ticket description passed
	 * @param ticketDesciption
	 */
	public void bookTheRoom(String ticketDesciption);
}
