package com.Planon.Library;

import java.util.List;
import java.util.Map;

public class Customer {

	private String customerName;
	private String customerType;
	private String customerId;
	private Map<String,Integer> subjectBooksIssued;
	private int issuedBooksCount;
	
	public Customer(String customerName, Map<String,Integer> subjectBooksIssued, String customerId, String customerType, int issuedBooksCount) {
		this.subjectBooksIssued=subjectBooksIssued;
		this.customerName=customerName;
		this.customerId=customerId;
		this.customerType=customerType;
		this.issuedBooksCount=issuedBooksCount;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Map<String, Integer> getSubjectBooksIssued() {
		return subjectBooksIssued;
	}

	public void setSubjectBooksIssued(Map<String, Integer> subjectBooksIssued) {
		this.subjectBooksIssued = subjectBooksIssued;
	}

	public int getIssuedBooksCount() {
		return issuedBooksCount;
	}

	public void setIssuedBooksCount(int issuedBooksCount) {
		this.issuedBooksCount = issuedBooksCount;
	}

}
