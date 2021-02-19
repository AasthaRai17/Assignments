package com.planon.client;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

import com.planon.customExceptions.UserNotFoundException;
import com.planon.employee.FacilityConsumer;
import com.planon.ticket.Ticket;
/**
 * this class handles the workflow wrt a facility consumer
 * functions and processes which can be done or are done by a consumer
 * @author aarai
 *
 */
public class FacilityConsumerFunctions {

	private static Logger logger = Logger.getLogger(FacilityConsumerFunctions.class.getName());
	private FacilityConsumer currentConsumer  = new FacilityConsumer();
	
	/**
	 * function is called if logged in user is a facility consumer
	 * wrt logged in consumer, options are given to raise a ticket of a specific type
	 * @param sc
	 */
	public void facilityConsumerLogin(Scanner sc) {
		//check id of consumer
		//if exist allow
		//else exit
		try {
			logger.info("Please enter your employee id to proceed: ");
			String empId = sc.nextLine();
			
			Optional<FacilityConsumer> currentlyLoggedConsumerOptional = StaticInputData.facilityConsumerList.stream().filter(person -> person.geteId().equals(empId)).findFirst();
			
			if(!currentlyLoggedConsumerOptional.isPresent())
				throw new UserNotFoundException(empId);
			
			currentConsumer = currentlyLoggedConsumerOptional.get();
			logger.info("Hello " + currentConsumer.geteName()+ ".");
			raiseANewTicketOption(sc);
			
		}catch(UserNotFoundException e) {
			e.getMessage(); 
			facilityConsumerLogin(sc); 
		}catch(Exception e) {
			logger.info("Exception in facilityConsumerLogin: " + e);
		}
		
	}

	/**
	 * function acts as per the input given if user wants to log anew ticket or view tickets
	 * @param sc
	 */
	private void raiseANewTicketOption(Scanner sc) {
		
		try {
			
			logger.info("Press 1 to log a new ticket, press 2 to view your tickets and press 0 to go back to previous menu.");
			int option = Integer.parseInt(sc.nextLine());
			
			switch(option) {
				case 1: raiseANewTicketTypeOption(sc);
				break;
				case 2: viewAllTicketsAndGoBackToPreviousMenu(sc);
				break;
				case 0: Client.facilityManagementWelcomePortal();
				break;
				default: {
					logger.info(FacilityConstants.WRONG_INPUT);
					raiseANewTicketOption(sc);
				}
			}
			
		}catch(Exception e) {
			logger.info("Exception in raiseANewTicketOption: " + e);
		}
	}

	
	private void viewAllTicketsAndGoBackToPreviousMenu(Scanner sc) {
		viewAllTickets();
		raiseANewTicketOption(sc);
	}
	/**
	 * function will print all the tickets logged by the current logged in consumer
	 */
	private void viewAllTickets() {
		List<Ticket> currentConsumerTickets = FacilityConstants.TICKET_GENERIC_FUNCTIONS.viewAllTicketsLoggedByAPerson(currentConsumer);
		FacilityConstants.TICKET_GENERIC_FUNCTIONS.printTicketDetais(currentConsumerTickets);
	}

	/**
	 * function will lead the process of raising a new ticket depending on the type selected by user
	 * type can be: request, complain or an incident
	 * @param sc
	 */
	private void raiseANewTicketTypeOption(Scanner sc) {
		try {
			
			logger.info("Press 1 for a Request, 2 to report an Incident, 3 to file a Complain and 0 to go back to previous menu.");
			int typeOfTicket = Integer.parseInt(sc.nextLine());
			
			if(typeOfTicket==0){
				raiseANewTicketOption(sc);
			}
			//ticket_types constant has the types as request,incident, complain.
			else if(!FacilityConstants.TICKET_TYPES.containsKey(typeOfTicket)) {
				logger.info(FacilityConstants.WRONG_INPUT);
				raiseANewTicketTypeOption(sc);
			}
			else{
				raiseANewTicketFacilityTypeOption(sc, FacilityConstants.TICKET_TYPES.get(typeOfTicket));
			}
			
		}catch(Exception e) {
			logger.info("Exception in raiseATicket: " + e);
		}
		
	}

	/**
	 * the function is providing options of facility that the ticket to be raised is for what facility
	 * and accordingly a ticket will be created
	 * @param sc
	 * @param typeOfTicket
	 */
	private void raiseANewTicketFacilityTypeOption(Scanner sc, String typeOfTicket) {
		
		try {
			logger.info("Please press 1 for Cleaning, 2 for Catering, 3 for Stationery, 4 for Food reimbursment, 5 for Room booking, 6 for Travel reimbursment.... and press 0 to go back to previous menu");
			int facilityTypeInput = Integer.parseInt(sc.nextLine());
			
			if(facilityTypeInput==0) 
				raiseANewTicketTypeOption(sc);
			else if(!FacilityConstants.FACILITY_TYPES.containsKey(facilityTypeInput)) {
				logger.info(FacilityConstants.WRONG_INPUT);
				raiseANewTicketFacilityTypeOption(sc,typeOfTicket);
			}
			else {
				logger.info("Please provide ticket description: ");
				String ticketDescription = sc.nextLine();
				createANewTicket(FacilityConstants.FACILITY_TYPES.get(facilityTypeInput), typeOfTicket, ticketDescription);
				raiseANewTicketFacilityTypeOption(sc, typeOfTicket);
			}
		}catch(Exception e) {
			logger.info("Exception in raiseANewTicketFacilityTypeOption: " + e);
		}
	}

	/**
	 * function creates a ticket with attributes as passed in the function
	 * @param ticketFacilityType
	 * @param typeOfTicket
	 * @param ticketDescription
	 */
	private void createANewTicket(String ticketFacilityType, String typeOfTicket, String ticketDescription) {
		FacilityConstants.TICKET_GENERIC_FUNCTIONS.createANewTicketWithDefaultOwnerAsFacilityMgr(ticketFacilityType, typeOfTicket, ticketDescription, currentConsumer);
	}

}
