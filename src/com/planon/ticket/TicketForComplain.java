package com.planon.ticket;

import com.planon.client.FacilityConstants;
import com.planon.employee.Employee;

/**
 * the class is to define a ticket which is type of complain
 * @author aasth
 *
 */
public class TicketForComplain extends Ticket {

	/**
	 * setting ticket type as complain ticket
	 */
	@Override
	public void setTicketType() {
		typeOfTicket = FacilityConstants.COMPLAIN_TICKET;
		
	}
	
	/**
	 * by default setting the status of ticket as "in pool"
	 * constructor to create a complain type of ticket
	 * @param ticketOwner
	 * @param ticketDescription
	 * @param ticketTypeBasedOnFacility
	 * @param ticketLoggedBy
	 * @param ticketAssignedToForWork
	 */
	public TicketForComplain(Employee ticketOwner, String ticketDescription, String ticketTypeBasedOnFacility,
			Employee ticketLoggedBy, Employee ticketAssignedToForWork) {
		super();
		this.ticketOwner = ticketOwner;
		this.ticketDescription = ticketDescription;
		this.ticketTypeBasedOnFacility = ticketTypeBasedOnFacility;
		this.ticketLoggedBy = ticketLoggedBy;
		this.ticketAssignedToForWork = ticketAssignedToForWork;
		this.ticketStatus = FacilityConstants.POOL_STATUS;
		setTicketType();
		setTicketId();
	}

	public TicketForComplain() {
		// TODO Auto-generated constructor stub
	}
	

}
