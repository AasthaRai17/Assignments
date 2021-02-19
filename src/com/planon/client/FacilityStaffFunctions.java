package com.planon.client;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import java.util.logging.Logger;

import com.planon.customExceptions.TicketNotFoundException;
import com.planon.customExceptions.UserNotFoundException;
import com.planon.employee.StaffMembers;
import com.planon.ticket.Ticket;

/**
 * this class is with respect to staff member login
 * all the process and functions to be done or are done are available in the class
 * @author aarai
 *
 */
public class FacilityStaffFunctions {
	
	Logger logger = Logger.getLogger(FacilityStaffFunctions.class.getName());
	//holds the value for current logged in staff member on 0th location
	private StaffMembers currentStaffMember;
	//holds the ticket which is currently being accessed by the staff member on 0th location
	private Ticket ticketToBeUpdated;
	
	/**
	 * function takes user input for staff id to proceed further
	 * @param sc
	 */
	public void facilityStaffLogin(Scanner sc) {
		
		try {
			logger.info("Hi staff member! Please provide your id to proceed: ");
			String staffId = sc.nextLine();
			Optional<StaffMembers> currentStaffMemberOptional = StaticInputData.staffMemberList.stream().filter(person -> person.getStaffId().equals(staffId)).findFirst();
			
			if(!currentStaffMemberOptional.isPresent())
				throw new UserNotFoundException(staffId);
			
			currentStaffMember = currentStaffMemberOptional.get();
			logger.info("Hello " + currentStaffMember.getStaffMemberName()+ ".");
			
			facilityStaffLoginOptions(sc);
		}catch(UserNotFoundException e) {
			e.getMessage();
			facilityStaffLogin(sc);
		}catch(Exception e) {
			logger.info("Exception in facilityStaffLogin: " + e);
		}
		
	}

	/**
	 * function provide options for staff  member to proceed to check tickets assigned to him
	 * or to update a ticket  
	 * @param sc
	 */
	private void facilityStaffLoginOptions(Scanner sc) {
		logger.info("Please press 1 to check tickets assigned to you, 2 to mark a ticket fixed and press 0 to go back to previous menu");
		int option = Integer.parseInt(sc.nextLine());
		
		switch(option) {
			case 1: checkAllticketsAssigned();
			break;
			case 2: updateATicket(sc);
			break;
			case 0: Client.facilityManagementWelcomePortal();
			break;
			default: {
						logger.info(FacilityConstants.WRONG_INPUT);
						facilityStaffLoginOptions(sc);
					}
		}
	}

	/**
	 * function gives the ability to update status of an existing ticket
	 * only if the ticket belongs to the respective logged in staff member
	 * @param sc
	 */
	private void updateATicket(Scanner sc) {
		try {
			
			logger.info("Enter the ticket number you want to update the status for:");
			String ticketNumber = sc.nextLine();
			Optional<Ticket> ticketToBeUpdatedOptional = StaticInputData.listOfTickets.stream().filter(ticket -> 
				ticket.getTicketId().equals(ticketNumber)
				&& ticket.getTicketAssignedToForWork().equals(currentStaffMember)
			).findFirst();
			
			if(!ticketToBeUpdatedOptional.isPresent())
				throw new TicketNotFoundException(ticketNumber);
			
			ticketToBeUpdated = ticketToBeUpdatedOptional.get();
			
			logger.info("Ticket to be updated is::");
			ticketToBeUpdated.getTicketDetails(ticketToBeUpdated);
			
			ticketStatusUpdateOptions(sc);
			
		}catch(TicketNotFoundException e) {
			e.getMessage();
			updateATicket(sc);
		}
		catch(Exception e) {
			logger.info("Exception in updateATicket: " + e);
		}
		
	}

	/**
	 * function provides options to update the ticket status as on hold or fixed
	 * @param sc
	 */
	private void ticketStatusUpdateOptions(Scanner sc) {
		
		try {
			
			logger.info("Press 1 to set the status as Fixed, press 2 to set the status as On hold, press 0 to go back to previous menu");
			int statusOption = Integer.parseInt(sc.nextLine());
			
			switch(statusOption) {
				case 1: updateTicketStatusByStaff(FacilityConstants.FIXED_STATUS);
				break;
				case 2: updateTicketStatusByStaff(FacilityConstants.ON_HOLD_STATUS);
				break;
				case 0: facilityStaffLoginOptions(sc);
					break;
				default: {
						logger.info(FacilityConstants.WRONG_INPUT);
						ticketStatusUpdateOptions(sc);
					}
			}
		}catch(Exception e) {
			logger.info("Exception in ticketStatusUpdateOptions: " + e);
		}
	}

	/**
	 * function updates the status of the ticket 
	 * @param status is the value of status to be updated
	 */
	private void updateTicketStatusByStaff(String status) {
		FacilityConstants.TICKET_GENERIC_FUNCTIONS.updateTicketStatusAndSendNotifaction(ticketToBeUpdated, status);
	}

	/**
	 * function is simply getting all the tickets assigned to the current staff member and printing the ticket details
	 */
	private void checkAllticketsAssigned() {
		
		try {
			
			List<Ticket> ticketAssigned = FacilityConstants.TICKET_GENERIC_FUNCTIONS.viewAllTicketsAssignedToAPerson(currentStaffMember);
			FacilityConstants.TICKET_GENERIC_FUNCTIONS.printTicketDetais(ticketAssigned);
		}catch(Exception e) {
			logger.info("Exception in checkAllticketsAssigned: " + e);
		}
		
	}
}
