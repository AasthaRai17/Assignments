package com.planon.client;

import java.util.ArrayList;
import java.util.List;

import com.planon.employee.CafeteriaManager;
import com.planon.employee.Employee;
import com.planon.employee.FacilityConsumer;
import com.planon.employee.FacilityManager;
import com.planon.employee.MaidStaff;
import com.planon.employee.OfficeBoy;
import com.planon.employee.SecurityGuards;
import com.planon.employee.StaffMembers;
import com.planon.ticket.Ticket;
import com.planon.ticket.TicketForComplain;
import com.planon.ticket.TicketForIncident;
import com.planon.ticket.TicketForRequest;

/**
 * the class has static variables to store the static data of tickets, staff members, consumers and facility manager
 * 
 * @author aarai
 *
 */
public class StaticInputData {

	public static Employee facilityManager = new FacilityManager();
	public static final List<FacilityConsumer> facilityConsumerList = new ArrayList<>();
	public static final List<StaffMembers> staffMemberList = new ArrayList<>();
	public static final List<CafeteriaManager> cafeteriaManagerList = new ArrayList<>();
	public static final List<MaidStaff> maidStaffMemberList = new ArrayList<>();
	public static final List<OfficeBoy> officeBoyMemberList = new ArrayList<>();
	public static final List<SecurityGuards> securityGuardMemberList = new ArrayList<>();
	public static final List<Ticket> listOfTickets = new ArrayList<>();
	
	/**
	 * creating static list of staff members as the existing data for use case purpose 
	 */
	static {
		officeBoyMemberList.add(new OfficeBoy("Gopal", "H.N. 123/90, gopalpur, Hyderabad", "OB001"));
		maidStaffMemberList.add(new MaidStaff("Madhav", "H.N. 45, Madhav nagar, Hyderabad", "M001"));
		securityGuardMemberList.add(new SecurityGuards("Laxman", "H.N. 89, laxman nagar, Hyderabad", "SG001"));
		officeBoyMemberList.add(new OfficeBoy("Lucky", "H.N. 12/90, gopalpur, Hyderabad", "OB002"));
		maidStaffMemberList.add(new MaidStaff("Raju", "H.N. 67, Madhav nagar, Hyderabad", "M002"));
		securityGuardMemberList.add(new SecurityGuards("Shyaam", "H.N. 78, laxman nagar, Hyderabad", "SG002"));
		cafeteriaManagerList.add(new CafeteriaManager("Babu laal", "H.N. 67, mulayam nagar, Hyderabad", "CM001"));
		staffMemberList.addAll(officeBoyMemberList);
		staffMemberList.addAll(maidStaffMemberList);
		staffMemberList.addAll(securityGuardMemberList);
		staffMemberList.addAll(cafeteriaManagerList);
	}

	/**
	 * creating static list of facility consumers as the existing data for use case purpose 
	 */
	static {
		facilityConsumerList.add(new FacilityConsumer("Monica Geller", "1034645", "monica.geller@planonsoftware.com", "Framework Development"));
		facilityConsumerList.add(new FacilityConsumer("Joey Tribbiani", "1034646", "joey.tribbiani@planonsoftware.com", "Client 1"));
		facilityConsumerList.add(new FacilityConsumer("Ross Geller", "1034647", "ross.geller@planonsoftware.com", "Framework Testing"));
		facilityConsumerList.add(new FacilityConsumer("Pheobe Buffay", "1034648", "pheobe.buffay@planonsoftware.com", "Client 2"));
		facilityConsumerList.add(new FacilityConsumer("Rachel Green", "1034649", "rachel.green@planonsoftware.com", "Framework Development"));
	}
	
	/**
	 * creating static facility manager as the existing data for use case purpose 
	 */
	static {
		facilityManager = new FacilityManager("Chandler Bing", "1034644");
	}
	
	/**
	 * creating static list of tickets as the existing data for use case purpose 
	 */
	static {
		listOfTickets.add(new TicketForComplain(facilityManager, "Clean the washroom number 2 on 1st floor.", FacilityConstants.CLEANING, facilityConsumerList.get(0), maidStaffMemberList.get(0)));
		listOfTickets.add(new TicketForComplain(facilityManager, "Not enough plates in cafeteria.", FacilityConstants.CATERING, facilityConsumerList.get(1), maidStaffMemberList.get(1)));
		listOfTickets.add(new TicketForRequest(facilityManager, "Please book a meeting room 4.", FacilityConstants.BOOK_A_ROOM, facilityConsumerList.get(0), officeBoyMemberList.get(0)));
		listOfTickets.add(new TicketForRequest(facilityManager, "Please book a meeting room 5.", FacilityConstants.BOOK_A_ROOM, facilityConsumerList.get(1), officeBoyMemberList.get(1)));
		listOfTickets.add(new TicketForIncident(facilityManager, "xyz entered without biometric.", FacilityConstants.SECURITY, facilityConsumerList.get(0), securityGuardMemberList.get(0)));
		listOfTickets.add(new TicketForIncident(facilityManager, "abc took a picture of business data.", FacilityConstants.SECURITY, facilityConsumerList.get(1), securityGuardMemberList.get(1)));
	}
	
	private StaticInputData() {
	}
}
