package com.Planon.Facebook;

import java.util.ArrayList;
import java.util.List;

//subject class 
public class StatusChangeFunctions {
	private List<TheStatusObserver> fObservers = new ArrayList<>();
	private String statusString;
	
	public StatusChangeFunctions() {
		new FacebookObserver(this);
		new WhatsappObserver(this);
		new InstagramObserver(this);
	}
	
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
		notifyAllObservers();
	}
	
	//calling the function to add observers to the list
	public void attach(TheStatusObserver observer) {
		fObservers.add(observer);
	}
	
	public void notifyAllObservers() {
		for(TheStatusObserver observer : fObservers) {
			observer.updateStatus();
		}
	}
}
