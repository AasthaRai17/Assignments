package com.Planon.Facebook;

import java.util.ArrayList;
import java.util.List;

public class StatusChangeFunctions {
	private List<TheStatusObserver> fObservers = new ArrayList<>();
	private String statusString;
	
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
		notifyAllObservers();
	}
	
	public void attach(TheStatusObserver observer) {
		fObservers.add(observer);
	}
	
	public void notifyAllObservers() {
		for(TheStatusObserver observer : fObservers) {
			observer.updateStatus();
		}
	}
}
