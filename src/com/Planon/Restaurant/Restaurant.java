package com.Planon.Restaurant;

import java.util.List;

import com.Planon.SalaryIncrement.EmployeeAttributes;

public class Restaurant implements Comparable<Restaurant>{

	private String restaurantName;
	private List<String> restaurantReviews;
	private int rank;
	private long goodWordCounts;
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getGoodWordCounts() {
		return goodWordCounts;
	}

	public void setGoodWordCounts(long goodWordCounts) {
		this.goodWordCounts = goodWordCounts;
	}

	public List<String> getRestaurantReviews() {
		return restaurantReviews;
	}
	
	public void setRestaurantReviews(List<String> restaurantReviews) {
		this.restaurantReviews = restaurantReviews;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	@Override
	public int compareTo(Restaurant compareRest) {
		long wordsCount = compareRest.getGoodWordCounts();
		return (int) (wordsCount - this.goodWordCounts);
	}
	

}
