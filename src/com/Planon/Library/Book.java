package com.Planon.Library;

public class Book {

	private String bookName;
	private String bookauthor;
	private int bookEditionYear;
	private String bookSubject;
	private String bookType;
	private int bookCount;
	private String bookId;
	private double bookPrice;
	private double salePriceOfBook;
	
	public Book(String bookName, String bookauthor, String bookSubject, String bookType, int bookEditionYear, int bookCount, String bookId, double bookPrice, double salePriceOfBook) {
		this.bookauthor=bookauthor;
		this.bookCount=bookCount;
		this.bookEditionYear=bookEditionYear;
		this.bookId=bookId;
		this.bookName=bookName;
		this.bookSubject=bookSubject;
		this.bookType=bookType;
		this.bookPrice=bookPrice;
		this.salePriceOfBook=salePriceOfBook;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public int getBookEditionYear() {
		return bookEditionYear;
	}
	public void setBookEditionYear(int bookEditionYear) {
		this.bookEditionYear = bookEditionYear;
	}
	public String getBookSubject() {
		return bookSubject;
	}
	public void setBookSubject(String bookSubject) {
		this.bookSubject = bookSubject;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public double getSalePriceOfBook() {
		return salePriceOfBook;
	}

	public void setSalePriceOfBook(double salePriceOfBook) {
		this.salePriceOfBook = salePriceOfBook;
	}
}
