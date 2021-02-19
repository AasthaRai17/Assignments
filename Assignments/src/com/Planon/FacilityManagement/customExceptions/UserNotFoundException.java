package com.planon.customExceptions;

/**
 * class for custom exceptions related to a user
 * which can be a staff member/ facility manager/ facility consumer
 * @author aarai
 *
 */
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String userId) {
		super("User with id " + userId + " not found.");
	}
}
