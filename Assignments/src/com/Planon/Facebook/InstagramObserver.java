package com.Planon.Facebook;

public class InstagramObserver extends TheStatusObserver {
	
	public InstagramObserver(StatusChangeFunctions instaStatus) {
		this.status = instaStatus;
		this.status.attach(this);
	}

	@Override
	public void updateStatus() {
		System.out.println("Instagram status updated as: " + status.getStatusString());

	}

}
