package com.planon.employee;

import java.util.logging.Logger;

import com.planon.client.FacilityConstants;
import com.planon.ticket.Ticket;

public class FacilityManager implements Employee {
	
	private static Logger logger = Logger.getLogger(FacilityManager.class.getName());
	String fmName;
	String fmId;
	Ticket fmTicket;
	
	public FacilityManager(String fMName, String fMId) {
		super();
		fmName = fMName;
		fmId = fMId;
	}
	
	
	public String getFmId() {
		return fmId;
	}


	public Ticket getFMticket() {
		return fmTicket;
	}

	public void setFMticket(Ticket fMticket) {
		fmTicket = fMticket;
	}

	/**
	 * function is assigning the ticket to an available and valid person
	 * and then sending a notification to the person to whom the ticket has been assigned.
	 * @param ticket
	 */
	public void assignTicket(Ticket ticket) {
		//depending on ticket decide which staff member to allocate the ticket
		StaffMembers staffToAssignTheTask = StaffMemberFactory.getAvailableStaffMemberForAFacility(ticket.getTicketTypeBasedOnFacility());
		ticket.setTicketAssignedToForWorkAndNotify(staffToAssignTheTask);
		ticket.setTicketStatus(FacilityConstants.ASSIGNED_STATUS);
	}
	
	public FacilityManager() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmployeeDetails() {
		return this.fmName + "(" + this.fmId + ")";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketNotification(Ticket ticket) {
		logger.info("Hi " + this.fmName +" (facility manager). A ticket has been logged by " + ticket.getTicketLoggedBy().getEmployeeDetails() + " for " + ticket.getTicketTypeBasedOnFacility() + ".");
		//assign the ticket
		assignTicket(ticket);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketUpdateNotification(Ticket ticket) {
		logger.info("Hi " + this.fmName +" (facility manager). Ticket number: " + ticket.getTicketId() + " has been updated.");
		//updateTicket(ticket);
	}

	/*private void updateTicket(Ticket ticket) {
		//depending on comments or other criteria
		//facility manager will mark the ticket closed or in progress
		ticket.setTicketStatus(FacilityConstants.CLOSED_STATUS);
		//a notification will be sent to staff and the ticket logger
		ticket.getTicketLoggedBy().ticketUpdateNotification(ticket);
		ticket.getTicketAssignedToForWork().ticketUpdateNotification(ticket);
		
	}*/
}
