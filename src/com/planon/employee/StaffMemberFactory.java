package com.planon.employee;

import com.planon.client.FacilityConstants;
import com.planon.client.StaticInputData;

/**
 * class returns available staff member as per the type of facility the ticket has been raised for
 * there can be a separate logic to find out which staff member is to be assigned as per their availability
 * for now there is no such logic. by default 1st person is the one who is assigned with all the tickets
 * wrt task type
 * @author aarai
 *
 */
public class StaffMemberFactory {
	
	public static StaffMembers getAvailableStaffMemberForAFacility(String facilityType) {
		switch(facilityType) {
			case FacilityConstants.CLEANING:
				return getAvailableMaid();
			case FacilityConstants.CATERING:
				return getAvailableMaid();
			case FacilityConstants.SECURITY:
				return getAvailSecurityGuard();
			case FacilityConstants.STATIONERY:
				return getAvailableOfficeBoy();
			case FacilityConstants.BOOK_A_ROOM:
				return getAvailableOfficeBoy();
			case FacilityConstants.FOOD_REIMBURSMENT:
				return getAvailCafeteriaManager();
			default:return null;
		}
	}
	
	/**
	 * function returns a maid type of object who is available to take up a ticket
	 * and work upon it
	 * @return
	 */
	private static MaidStaff getAvailableMaid() {
		if(!StaticInputData.maidStaffMemberList.isEmpty()) {
			return StaticInputData.maidStaffMemberList.get(0);
		}
		return null;
	}
	
	/**
	 * function returns a OfficeBoy type of object who is available to take up a ticket
	 * and work upon it
	 * @return
	 */
	private static OfficeBoy getAvailableOfficeBoy() {
		if(!StaticInputData.officeBoyMemberList.isEmpty()) {
			return StaticInputData.officeBoyMemberList.get(0);
		}
		return null;
	}
	
	/**
	 * function returns a SecurityGuards type of object who is available to take up a ticket
	 * and work upon it
	 * @return
	 */
	private static SecurityGuards getAvailSecurityGuard() {
		if(!StaticInputData.securityGuardMemberList.isEmpty()) {
			return StaticInputData.securityGuardMemberList.get(0);
		}
		return null;
	}
	
	/**
	 * function returns a CafeteriaManager type of object who is available to take up a ticket
	 * and work upon it
	 * @return
	 */
	private static CafeteriaManager getAvailCafeteriaManager() {
		if(!StaticInputData.cafeteriaManagerList.isEmpty()) {
			return StaticInputData.cafeteriaManagerList.get(0);
		}
		return null;
	}
	
	private StaffMemberFactory() {
	}
}
