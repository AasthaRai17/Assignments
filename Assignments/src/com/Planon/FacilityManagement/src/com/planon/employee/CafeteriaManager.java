package com.planon.employee;

import java.util.logging.Logger;

import com.planon.client.FacilityConstants;
import com.planon.facilities.Catering;
import com.planon.facilities.FoodReimbursment;
import com.planon.ticket.Ticket;

public class CafeteriaManager implements StaffMembers, FoodReimbursment, Catering{

	
	private Logger logger = Logger.getLogger(CafeteriaManager.class.getName());
	
	String cFManagerName;
	String cFManagerAddress;
	String cFManagerId;
	
	public CafeteriaManager(String cFManagerName, String cFManagerAddress, String cFManagerId) {
		super();
		this.cFManagerName = cFManagerName;
		this.cFManagerAddress = cFManagerAddress;
		this.cFManagerId = cFManagerId;
	}
	
	public CafeteriaManager() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmployeeDetails() {
		return "Cafeteria manager " + this.cFManagerName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketNotification(Ticket ticket) {
		logger.info("Hi " + this.cFManagerName + " (cafeteria manager). A ticket has been assigned to you.");
		logger.info(ticket.getTicketDetails(ticket));
		ticket.setTicketStatus(FacilityConstants.IN_PROGRESS_STATUS);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketUpdateNotification(Ticket ticket) {
		logger.info("Hi " + this.cFManagerName +" (cafeteria manager). Ticket number " + ticket.getTicketId() + " has been marked " + ticket.getTicketStatus());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculateTheProjectSpecificBudget() {
		logger.info("calculating the budget left for the project");
		passTheCalculationToFinanceDeptForReimbursement();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void passTheCalculationToFinanceDeptForReimbursement() {
		logger.info("once the calculation is done the ticket moves in finance dept queue.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doTheAssignedTask(Ticket ticket) {
		//depending on the ticket description decide the task of cafeteria manager and call the respective function
		calculateTheProjectSpecificBudget();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void checkTheCateringServiceIssue(String ticketDesciption) {
		logger.info("checkTheCateringServiceIssue as per the ticket Desciption: " + ticketDesciption);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initiateTheFoodReimbursementProcess(String ticketDesciption) {
		logger.info("initiateTheFoodReimbursementProcess as per the ticket Desciption: " + ticketDesciption);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaffId() {
		return this.cFManagerId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaffMemberName() {
		return this.cFManagerName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cFManagerAddress == null) ? 0 : cFManagerAddress.hashCode());
		result = prime * result + ((cFManagerId == null) ? 0 : cFManagerId.hashCode());
		result = prime * result + ((cFManagerName == null) ? 0 : cFManagerName.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CafeteriaManager other = (CafeteriaManager) obj;
		if (cFManagerAddress == null) {
			if (other.cFManagerAddress != null)
				return false;
		} else if (!cFManagerAddress.equals(other.cFManagerAddress))
			return false;
		if (cFManagerId == null) {
			if (other.cFManagerId != null)
				return false;
		} else if (!cFManagerId.equals(other.cFManagerId))
			return false;
		if (cFManagerName == null) {
			if (other.cFManagerName != null)
				return false;
		} else if (!cFManagerName.equals(other.cFManagerName))
			return false;
		return true;
	}


}
