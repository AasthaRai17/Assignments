package com.planon.client;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * As a facility manager I do facilitate things in the office for employees which includes managers. 

People do approach me asking/requesting/complaining... 

Can I have a pen black in color //stationary
I need a board marker  //stationary
The toilet stinks //cleaning
manager: We have on-site guests visiting. I need to use board room for coming two days. And yes, take care of lunch for 5 people. //room booking, food reimbursement
Team stayed later yesterday working on a critical item. We had dinner in office. Please reimburse it. My manager told me to check with you. //food reimbursement
The cafeteria does not have enough cutlery. Please ask the maid to do the dishes on priority. //catering
My desk key is lost. I can't start my work since my laptop is inside. Please help. //workstation

---------------- 
Design a system which helps facility manager to manage things. 
An employee/manager should be able to create incident/request/reservation/complaint/etc. using the system. 
The facility manager gets an overview of what people want and pick them based on the priority which is in 
the request. and Assign staff like office boy, security, maid to it if needed. 
 */


/**
 * This is the main class which directly interacts with the user
 * depending on the user who is accessing the facility management system the flow will be handled
 * @author aarai
 *
 */
public class Client {

	private static  Logger logger = Logger.getLogger(Client.class.getName());
	
	public static void main(String[] args) {
		
		try {
			
			facilityManagementWelcomePortal();
			
		}catch(Exception e) {
			logger.info("Exception in main: " + e);
		}
	}

	/**
	 * function provides the welcome options in facility management to direct the flow as per the logged in user
	 */
	public static void facilityManagementWelcomePortal() {
		
		Scanner sc = new Scanner(System.in);
		try {
			
			logger.info("Welcome to Facility Management Portal. Press 1 if you are a Facility Manager, press 2 if a Facility Consumer and press 3 if a Staff Member.");
			int loginType = Integer.parseInt(sc.nextLine());
			
			switch(loginType) {
				case 1: facilityManagerProcess(sc);
					break;
				case 2: facilityConsumerProcess(sc);
					break;
				case 3: staffMemberProcess(sc);
					break;
				default: logger.info("You are not allowed!");
			}
			
		}catch(Exception e) {
			logger.info("Exception in facilityManagementWelcomePortal: " + e);
		}finally {
			sc.close();
		}
	}
	
	/**
	 * if the login is by a staff member
	 * @param sc 
	 */
	private static void staffMemberProcess(Scanner sc) {
		FacilityStaffFunctions staffFn = new FacilityStaffFunctions();
		staffFn.facilityStaffLogin(sc);
	}

	/**
	 * if the login is by a facility consumer
	 * @param sc 
	 */
	private static void facilityConsumerProcess(Scanner sc) {
		FacilityConsumerFunctions facilityConsumerFn = new FacilityConsumerFunctions();
		facilityConsumerFn.facilityConsumerLogin(sc);
	}

	/**
	 * if the login is by a facility manager
	 * @param sc 
	 */
	private static void facilityManagerProcess(Scanner sc) {
		FacilityManagerFunctions facilityMgrFn = new FacilityManagerFunctions();
		facilityMgrFn.facilitManagerLogin(sc);
	}
}
