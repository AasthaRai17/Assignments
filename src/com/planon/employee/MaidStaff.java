package com.planon.employee;

import java.util.logging.Logger;

import com.planon.client.FacilityConstants;
import com.planon.facilities.Cleaning;
import com.planon.ticket.Ticket;

public class MaidStaff implements StaffMembers, Cleaning {
	
	public static  Logger logger = Logger.getLogger(MaidStaff.class.getName());
	
	String maidName;
	String maidAddress;
	String staffId;
	
	public MaidStaff(String maidName, String maidAddress, String maidId) {
		super();
		this.maidName = maidName;
		this.maidAddress = maidAddress;
		this.staffId = maidId;
	}
	
	public MaidStaff() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doTheCleaning(String ticketDesciption) {
		logger.info("cleaning");
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doTheAssignedTask(Ticket ticket) {
		//depending on ticket description decide the task to be done
		logger.info("cleaning as per the ticket: " + ticket.getTicketDetails(ticket));
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmployeeDetails() {
		return "Maid " + this.maidName;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketNotification(Ticket ticket) {
		logger.info("Hi " + this.maidName + " (maid). A ticket has been assigned to you.");
		logger.info(ticket.getTicketDetails(ticket));
		ticket.setTicketStatus(FacilityConstants.IN_PROGRESS_STATUS);
		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketUpdateNotification(Ticket ticket) {
		logger.info("Hi " + this.maidName +" (maid). Ticket number " + ticket.getTicketId() + " has been marked " + ticket.getTicketStatus());
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaffId() {
		return this.staffId;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaffMemberName() {
		return this.maidName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maidAddress == null) ? 0 : maidAddress.hashCode());
		result = prime * result + ((maidName == null) ? 0 : maidName.hashCode());
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaidStaff other = (MaidStaff) obj;
		if (maidAddress == null) {
			if (other.maidAddress != null)
				return false;
		} else if (!maidAddress.equals(other.maidAddress))
			return false;
		if (maidName == null) {
			if (other.maidName != null)
				return false;
		} else if (!maidName.equals(other.maidName))
			return false;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}

}
