package com.planon.ticket;

import com.planon.client.FacilityConstants;

/**
 * this class has only one function which returns the tickettype object depending on the ticket type
 * passed as argument.
 * @author aasth
 *
 */
public class TicketFactory {
	
	/**
	 * returns the ticket type object depending on the ticket type
	 * passed as argument.
	 * @param ticketType to define what type of ticket object to be returned
	 * @return ticket object depending on the ticket type passed as argument
	 */
	public static Ticket getTicketType(String ticketType) {
		
		switch(ticketType) {
			case FacilityConstants.REQUEST_TICKET:
				return new TicketForRequest();
			case FacilityConstants.COMPLAIN_TICKET:
				return new TicketForComplain();
			case FacilityConstants.INCIDENT_TICKET:
				return new TicketForIncident();
			default:return null;
		}
		
	}
}
