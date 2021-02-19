package com.planon.employee;

import java.util.logging.Logger;

import com.planon.client.Client;
import com.planon.client.FacilityConstants;
import com.planon.facilities.RoomBookingManagement;
import com.planon.facilities.StationeryManagement;
import com.planon.facilities.WorkstationManagement;
import com.planon.ticket.Ticket;

public class OfficeBoy implements StaffMembers, StationeryManagement, WorkstationManagement, RoomBookingManagement {
	
	
	private Logger logger = Logger.getLogger(OfficeBoy.class.getName());
	
	String officeBoyName;
	String officeBoyAddress;
	String officeBoyId;

	public OfficeBoy(String officeBoyName, String officeBoyAddress, String officeBoyId) {
		super();
		this.officeBoyName = officeBoyName;
		this.officeBoyAddress = officeBoyAddress;
		this.officeBoyId = officeBoyId;
	}
	
	public OfficeBoy() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bookTheRoom(String ticketDesciption) {
		logger.info("Booking a room.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void checkTheWorkstationIssue(String ticketDesciption) {
		logger.info("Fixing the drawer issue");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assignTheStationeryToUser(String ticketDesciption) {
		logger.info("gave the stationary item to the person who asked for it.");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaffId() {
		return this.officeBoyId;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaffMemberName() {
		return this.officeBoyName;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doTheAssignedTask(Ticket ticket) {
		//depending on the ticket description decide the task of office boy and call the respective function
		logger.info("working as per the ticket: " + ticket.getTicketDetails(ticket));
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmployeeDetails() {
		return "Office boy " + this.officeBoyName;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketNotification(Ticket ticket) {
		logger.info("Hi " + this.officeBoyName + " (office boy). A ticket has been assigned to you.");
		logger.info(ticket.getTicketDetails(ticket));
		ticket.setTicketStatus(FacilityConstants.IN_PROGRESS_STATUS);
		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ticketUpdateNotification(Ticket ticket) {
		logger.info("Hi " + this.officeBoyName +" (office boy). Ticket number " + ticket.getTicketId() + " has been marked " + ticket.getTicketStatus());
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((officeBoyAddress == null) ? 0 : officeBoyAddress.hashCode());
		result = prime * result + ((officeBoyId == null) ? 0 : officeBoyId.hashCode());
		result = prime * result + ((officeBoyName == null) ? 0 : officeBoyName.hashCode());
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
		OfficeBoy other = (OfficeBoy) obj;
		if (officeBoyAddress == null) {
			if (other.officeBoyAddress != null)
				return false;
		} else if (!officeBoyAddress.equals(other.officeBoyAddress))
			return false;
		if (officeBoyId == null) {
			if (other.officeBoyId != null)
				return false;
		} else if (!officeBoyId.equals(other.officeBoyId))
			return false;
		if (officeBoyName == null) {
			if (other.officeBoyName != null)
				return false;
		} else if (!officeBoyName.equals(other.officeBoyName))
			return false;
		return true;
	}


}
