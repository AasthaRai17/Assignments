package com.planon.ticket;

import com.planon.client.FacilityConstants;
import com.planon.client.StaticInputData;
import com.planon.employee.Employee;

/**
 * the class is to define a ticket which is type of incident
 * @author aasth
 *
 */
public class TicketForIncident extends Ticket {
	
	/**
	 * setting ticket type as incident ticket
	 */
	@Override
	public void setTicketType() {
		typeOfTicket = FacilityConstants.INCIDENT_TICKET;
		
	}
	
	/**
	 * by default setting the status of ticket as "in pool"
	 * constructor to create a incident type of ticket
	 * @param ticketOwner
	 * @param ticketDescription
	 * @param ticketTypeBasedOnFacility
	 * @param ticketLoggedBy
	 * @param ticketAssignedToForWork
	 */
	public TicketForIncident(Employee ticketOwner, String ticketDescription, String ticketTypeBasedOnFacility,
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

	public TicketForIncident() {
		// TODO Auto-generated constructor stub
	}
	
}
