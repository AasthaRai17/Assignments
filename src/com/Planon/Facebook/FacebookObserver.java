package com.Planon.Facebook;

public class FacebookObserver extends TheStatusObserver {
	
	public FacebookObserver(StatusChangeFunctions facebookStatus) {
		this.status = facebookStatus;
		this.status.attach(this);
	}

	@Override
	public void updateStatus() {
		System.out.println("Facebook status updated as: " + status.getStatusString());

	}

}
