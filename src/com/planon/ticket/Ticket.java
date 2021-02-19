package com.planon.ticket;

import com.planon.client.StaticInputData;
import com.planon.employee.Employee;

/**
 * this class denotes a ticket which has some basic attributes
 * and the getter/setter for the same
 * it is an abstract class, has an abstract function to set type of ticket
 * which is further being overriden by 3 classes to set the ticket type depending on what object to be created.
 * @author aasth
 *
 */
public abstract class Ticket {

	Employee ticketOwner;
	String ticketDescription;
	String ticketTypeBasedOnFacility;
	Employee ticketLoggedBy;
	String ticketId;
	Employee ticketAssignedToForWork;
	String typeOfTicket;
	String ticketStatus;

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getTicketTypeBasedOnFacility() {
		return ticketTypeBasedOnFacility;
	}

	public void setTicketTypeBasedOnFacility(String ticketTypeBasedOnFacility) {
		this.ticketTypeBasedOnFacility = ticketTypeBasedOnFacility;
	}

	public Employee getTicketLoggedBy() {
		return ticketLoggedBy;
	}

	public void setTicketLoggedBy(Employee ticketLoggedBy) {
		this.ticketLoggedBy = ticketLoggedBy;
	}

	public String getTicketId() {
		return ticketId;
	}

	/**
	 * default ticket id is being generated by +1 count of the existing ticket list size.
	 */
	public void setTicketId() {
		this.ticketId = String.valueOf(StaticInputData.listOfTickets.size()+1);
	}

	public Employee getTicketAssignedToForWork() {
		return ticketAssignedToForWork;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	/**
	 * function returns basic ticket detail, more details can also be added as per
	 * the requirement
	 * @param ticket
	 * @return string of ticket detail
	 */
	public String getTicketDetails(Ticket ticket) {
		return "Ticket number: " + ticket.ticketId + "(" + ticket.ticketDescription + ").";
	}
	
	/**
	 * function assigns the passed ticket and sends an notification to the assigned person
	 * @param ticketAssignedToForWork
	 */
	public void setTicketAssignedToForWorkAndNotify(Employee ticketAssignedToForWork) {
		this.ticketAssignedToForWork = ticketAssignedToForWork;
		ticketAssignedToForWork.ticketNotification(this);
	}
	
	/**
	 * function sets the ticket owner of the ticket and sends notification to the ticket owner
	 * @param ticketOwner
	 */
	public void setTicketOwnerAndNotify(Employee ticketOwner) {
		this.ticketOwner = ticketOwner;
		//notify the owner that ticket has been tagged to you
		ticketOwner.ticketNotification(this);
	}
	
	/**
	 * as we have 3 types of tickets, using this function ticket type is being set to the ticket
	 * depending on the ticket user is raising.
	 */
	public abstract void setTicketType();

}
