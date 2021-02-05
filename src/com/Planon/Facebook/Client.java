package com.Planon.Facebook;

public class Client {

	public static void main(String[] args) {
		
		StatusChangeFunctions status = new StatusChangeFunctions();
		
		String statusString = "Dream big!!";
		System.out.println("First status change::");
		status.setStatusString(statusString);
		statusString = "Dont just dream, work for it.";
		System.out.println("Second status change::");
		status.setStatusString(statusString);
	}

}
