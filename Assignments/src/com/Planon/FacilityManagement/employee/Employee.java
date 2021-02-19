package com.planon.employee;

import com.planon.ticket.Ticket;

public interface Employee {

	/**
	 * function provides the employee details in brief in form of a string just for printing purpose
	 * eg: Ticket 1234 (ticket description) 
	 * @return
	 */
	String getEmployeeDetails();

	/**
	 * a notification is sent (which can be an email) when a ticket is logged for the first time
	 * depending on the implementation of the function the notification is sent to staff member/consumer and facility mgr
	 * @param ticket
	 */
	void ticketNotification(Ticket ticket);

	/**
	 * a notification is sent(which can be an email) when a ticket's status is updated.
	 * depending on the implementation of the function the notification is sent to staff member/consumer and facility mgr
	 * @param ticket
	 */
	void ticketUpdateNotification(Ticket ticket);

}
