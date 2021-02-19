package com.planon.employee;

import com.planon.ticket.Ticket;

public interface StaffMembers extends Employee{
	public void doTheAssignedTask(Ticket ticket);
	public String getStaffId();
	public String getStaffMemberName();
}
