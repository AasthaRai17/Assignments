package com.planon.facilities;
/**
 * there can be multiple types of reimbursment to be handles
 * eg food/travel/business guests etc
 * for the purpose the interface can be used to have the functions related to it
 * @author aarai
 *
 */
public interface ReimbursmentManagement{

	/**
	 * just some generic functions which can be used to manage the reimbursement process and budget wrt projects
	 * in the organization
	 */
	public void calculateTheProjectSpecificBudget();
	public void passTheCalculationToFinanceDeptForReimbursement();
}
