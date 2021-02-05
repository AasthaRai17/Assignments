package com.Planon.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;



//add a book(s) in the library
//issue book(s) from the library to a person
//get all the books issued by one person
//set price of books 
//lambda expression


public class LibraryProcesses {

	private List<Book> bookList = new ArrayList<>();
	private List<Customer> customerList = new ArrayList<>();
	private final static Logger logger = Logger.getLogger(LibraryProcesses.class.getName());
	
	/**
	 * landing function of the class 
	 */
	public void LibraryProcessing() {
		libraryProcessStart();
	}

	
	/**
	 * initial function to take inputs 
	 */
	private void libraryProcessStart() {
		
		try {
			inputBooks();
			inputCustomers();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Press 1: Check all book details. 2: Set discount on books. 3: Check how many books are issued to one person.");
			int option = sc.nextInt();
			
			switch(option) {
				case 1: printBookDetails();
						break;
				case 2: setDiscountOnBooks();
						break;
				case 3: getCustomerInformation();
						break;
				default: System.out.println("Invalid option selected.");
			}
			
		}catch(Exception e) {
			logger.info("Exception in LibraryProcesses: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * set specific discount on one subject books
	 * @param subject is the subject of books for which discount to be done
	 * @param saleFactor is the discount percentage
	 */
	private void setNewSalePricesOfSpecificSubject(String subject, double saleFactor) {
		
		List<Book> updatedBookList = new ArrayList<>();
		try {
			updatedBookList = bookList
			.stream()
			.filter(book -> book.getBookSubject().equalsIgnoreCase(subject))
			.map(book -> {
							book.setSalePriceOfBook(book.getBookPrice() - (book.getBookPrice()*saleFactor));
							return book;
						 }).collect(Collectors.toList());
			System.out.println("length of updatedBookList: " + updatedBookList.size());
			printBookDetails();
			
		}catch(Exception e) {
			logger.info("Exception in setNewSalePricesOfSpecificSubject: " + e);
			e.printStackTrace();
				
		}
	}
	
	/**
	 * to print one customer's all the issued books
	 */
	private void getCustomerInformation() {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter customer id: ");
			String customerId = sc.nextLine();
			//customerId="C003";
			
			System.out.println("Printing details using stream and foreach api: ");
			//test;
			customerList
				.stream()
				.filter(customer -> 
					customer.getCustomerId().equalsIgnoreCase(customerId)//filtering the data wrt passed customer id
				).forEach(customer -> {
					customer.getSubjectBooksIssued()//once the customer id found, getting the issued books count 
						.entrySet()//and creating the entry set on the map
							.stream()
								.forEach(bookCountMap -> System.out.println(bookCountMap.getKey() + " : " + bookCountMap.getValue()));
				});
			
			System.out.println("Printing details using entry set: ");
			
			for(Customer oneCustomer : customerList) {
				//checking for the customer id given as input
				if(customerId.equalsIgnoreCase(oneCustomer.getCustomerId())) {
					//getting the issued books count on the basis of subjects
					Map<String, Integer> subjectBookCount = new HashMap<>();
					subjectBookCount = oneCustomer.getSubjectBooksIssued();
					//looing over theentry set of map
					for(Map.Entry<String, Integer> oneBookEntry : subjectBookCount.entrySet()){
						System.out.println(oneBookEntry.getKey() + " : " + oneBookEntry.getValue());
					}
				}
			}
			
			
		}catch(Exception e) {
			logger.info("Exception in getCustomerInformation: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * taking input for discount and subject on which discount to be given
	 */
	private void setDiscountOnBooks() {
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter discount percentage:");
			double discountPer = sc.nextDouble();
			
			sc = new Scanner(System.in);
			System.out.println("Enter subject of books for which discount to be given:");
			String subject = sc.nextLine();
			
			setNewSalePricesOfSpecificSubject(subject, (discountPer/100));
			
		}catch(Exception e) {
			logger.info("Exception in setDiscountOnBooks: " + e);
			e.printStackTrace();
		}
	}

/**
 * function to print book's name and sale price
 */
	private void printBookDetails() {
		
		try {
			bookList.forEach(book -> {
				StringBuilder bookDetails = new StringBuilder();
				bookDetails
					.append(book.getBookName())
					.append(" || ")
					.append(book.getBookauthor())
					.append(" || ....Sale Price is----- ")
					.append(book.getSalePriceOfBook());
				System.out.println(bookDetails);
			});
		}catch(Exception e) {
			logger.info("Exception in printAllBookDetails: " + e);
			e.printStackTrace();
		}
		
	}

/**
 * hard coded input for books
 */
	private void inputBooks() {
		
		bookList.add(new Book("The Complete Reference","Cay S. Horstmann","Java","Knowledge",2011,89,"B0001",1400,1400));
		bookList.add(new Book("Einstein Adds a New Dimension","Joy Hakim","Physics","Knowledge",2007,56,"B0002",1000,1000));
		bookList.add(new Book("Java Concurrency in Practice","Brian Goetz","Java","Knowledge",2001,17,"B0003",699,699));
		bookList.add(new Book("Figuring: The Joy of Numbers","Shakuntala Devi","Maths","Knowledge",1986,78,"B0004",599,599));
		bookList.add(new Book("More Puzzles to Puzzle You","Shakuntala Devi","Maths Puzzle","Knowledge",1976,34,"B0005",600,600));
		bookList.add(new Book("The Alchemist","Paulo Coelho","Inspiration","Fiction",2011,28,"B0006",449,449));
		bookList.add(new Book("The Richest Man in Babylon","Classon George S","Inspiration","Fiction",2011,89,"B0007",700,700));
		bookList.add(new Book("The Alkaloids","Hans Joachim Knolker","Chemistry","Knowledge",2017,56,"B0008",800,800));
		bookList.add(new Book("Gitanjali","Rabindranath Tagore","Poem","Ficton",1910,17,"B0009",670,670));
		bookList.add(new Book("Steve Jobs","Walter Isaacson","Biography","Non-Fiction",2011,78,"B0010",900,900));
 	}
	
	/**
	 * hard coded inputs for customers
	 */
	private void inputCustomers() {
		
		Map<String,Integer> subjectBooksIssued = new HashMap<>();
		subjectBooksIssued.put("Java", 2);
		subjectBooksIssued.put("Poem", 1);
		subjectBooksIssued.put("Biography", 1);
		customerList.add(new Customer("Babita", subjectBooksIssued,"C001","Non-Student",4));
		subjectBooksIssued = new HashMap<>();
		subjectBooksIssued.put("Java", 2);
		subjectBooksIssued.put("Poem", 1);
		subjectBooksIssued.put("Chemistry", 1);
		customerList.add(new Customer("Vivek", subjectBooksIssued,"C002","Student",4));
		subjectBooksIssued = new HashMap<>();
		subjectBooksIssued.put("Physics", 1);
		subjectBooksIssued.put("Maths", 1);
		subjectBooksIssued.put("Maths Puzzle", 1);
		subjectBooksIssued.put("Inspiration", 1);
		subjectBooksIssued.put("Biography", 1);
		customerList.add(new Customer("Anant", subjectBooksIssued,"C003","Non-Student",5));
	}
	
	/*
	 * private void addNewBookInLibrary(Book newBookToBeAdded) {
	 * bookList.add(newBookToBeAdded); }
	 * 
	 * private void addNewCustomerInLibrary(Customer newCustomerToBeAdded) {
	 * customerList.add(newCustomerToBeAdded); }
	 */
}
