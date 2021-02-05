package com.Planon.Facebook;

public class WhatsappObserver extends TheStatusObserver {
	
	public WhatsappObserver(StatusChangeFunctions whatsappStatus) {
		this.status = whatsappStatus;
		this.status.attach(this);
	}

	@Override
	public void updateStatus() {
		System.out.println("Whatsapp status updated as: " + status.getStatusString());

	}

}
