package com.planon.customExceptions;

/**
 * class for custom exceptions related to a ticket
 * @author aarai
 *
 */
public class TicketNotFoundException  extends Exception {
	public TicketNotFoundException(String ticketNumber) {
		super("Ticket " + ticketNumber + " not found.");
	}
}
