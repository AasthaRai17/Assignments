package com.planon.genericFacilityUtilityFunctions;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.planon.client.StaticInputData;
import com.planon.employee.Employee;
import com.planon.ticket.Ticket;
import com.planon.ticket.TicketFactory;
/**
 * class consists of generic functions which work on ticket type data
 * @author aarai
 *
 */
public class GenericTicketFunctions {
	
	Logger logger = Logger.getLogger(GenericTicketFunctions.class.getName());
	/**
	 * function returns a list of tickets which have been logged by the employee passed as parameter
	 * @param loggedBy
	 * @return
	 */
	public List<Ticket> viewAllTicketsLoggedByAPerson(final Employee loggedBy) {
		
		return  StaticInputData.listOfTickets
				.stream()
				.filter(oneTicket -> oneTicket.getTicketLoggedBy().equals(loggedBy))
				.collect(Collectors.toList());
		
	}

	/**
	 * function just prints the basic details of the tickets passed as parameter 
	 * @param listOfTickets
	 */
	public void printTicketDetais(List<Ticket> listOfTickets) {
		//print all tickets
		listOfTickets.forEach(ticket -> logger.info(ticket.getTicketDetails(ticket)));
	}
	
	/**
	 * function returns a list of tickets which are assigned to the employee passed
	 * as parameter in the function
	 * @param assignedTo
	 * @return
	 */
	public List<Ticket> viewAllTicketsAssignedToAPerson(Employee assignedTo) {
		
		return  StaticInputData.listOfTickets
				.stream()
				.filter(oneTicket -> oneTicket.getTicketAssignedToForWork().equals(assignedTo))
				.collect(Collectors.toList());
		
	}
	
	/**
	 * function creates a ticket with owner as Facility Manager and the parameters passed in the function
	 * @param ticketFacilityType
	 * @param typeOfTicket
	 * @param ticketDescription
	 * @param loggedBy
	 * @return
	 */
	public Ticket createANewTicketWithDefaultOwnerAsFacilityMgr(String ticketFacilityType, String typeOfTicket, String ticketDescription, Employee loggedBy) {
		
		Ticket ticket = TicketFactory.getTicketType(typeOfTicket);
		
		try {
			ticket.setTicketId();
			ticket.setTicketDescription(ticketDescription);
			ticket.setTicketType();
			ticket.setTicketTypeBasedOnFacility(ticketFacilityType);
			ticket.setTicketLoggedBy(loggedBy);
			ticket.setTicketOwnerAndNotify(StaticInputData.facilityManager);
			
			StaticInputData.listOfTickets.add(ticket);
		}catch(Exception e) {
			logger.info("Exception in createANewTicketWithDefaultOwnerAsFacilityMgr: " + e);
		}
		return ticket;
	}
	
	/**
	 * function calls the send notification function for all the tickets passed in the function as parameter
	 * the function calls the update ticket notification not the create ticket.
	 * @param tickets
	 */
	public void ticketUpdateNotificationToAllStakeholders(Ticket ticket) {
		try {
				
			if(null != ticket.getTicketLoggedBy())
				ticket.getTicketLoggedBy().ticketUpdateNotification(ticket);
			if(null != ticket.getTicketAssignedToForWork())
				ticket.getTicketAssignedToForWork().ticketUpdateNotification(ticket);
			
			StaticInputData.facilityManager.ticketUpdateNotification(ticket);
			
		}catch(Exception e) {
			logger.info("Exception in ticketUpdateNotificationToAllStakeholders: " + e);
		}
		
	}
	
	/**
	 * this function send the notification to all stakeholders of the ticket passed as parameter
	 * after updating the status of the same. Status is also passed in the parameter which will be set for the passed ticket
	 * @param ticket
	 * @param status
	 */
	public void updateTicketStatusAndSendNotifaction(Ticket ticket, String status) {
		
		try {
			ticket.setTicketStatus(status);
			ticketUpdateNotificationToAllStakeholders(ticket);
		}catch(Exception e) {
			logger.info("Exception in updateTicketStatusAndSendNotifaction: " + e);
		}
		
	}
}
