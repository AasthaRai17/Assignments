package com.planon.ticket;

import com.planon.client.FacilityConstants;
import com.planon.employee.Employee;

/**
 * the class is to define a ticket which is type of request
 * @author aasth
 *
 */
public class TicketForRequest extends Ticket {
	
	/**
	 * setting ticket type as request type of ticket
	 */
	@Override
	public void setTicketType() {
		typeOfTicket = FacilityConstants.REQUEST_TICKET;
		
	}

	/**
	 * by default setting the status of ticket as "in pool"
	 * constructor to create a request type of ticket
	 * @param ticketOwner
	 * @param ticketDescription
	 * @param ticketTypeBasedOnFacility
	 * @param ticketLoggedBy
	 * @param ticketAssignedToForWork
	 */
	public TicketForRequest(Employee ticketOwner, String ticketDescription, String ticketTypeBasedOnFacility,
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

	public TicketForRequest() {
 	}
}
