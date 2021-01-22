package com.Planon.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.Planon.SalaryIncrement.EmployeeSalaryProcess;

import java.util.TreeMap;

public class RestaurantRankingProcess {

	final static RestaurantConstants restConst = new RestaurantConstants();
	private final static Logger logger = Logger.getLogger(EmployeeSalaryProcess.class.getName());
	/**
	 * landing function for the restaurant ranking process
	 */
	public void rankTheRestaurants() {
		ArrayList<Restaurant> reviewList = inputReviews();
		countTheGoodWords(reviewList);
	}
	
	/**
	 * function to count the good words on the basis of which ranks are being allocated
	 * @param reviewList is the list of restaurant type objects which has review list
	 */
	private void countTheGoodWords(ArrayList<Restaurant> reviewList) {
		
		LinkedHashMap<String, Integer> countingList = new LinkedHashMap<>();
		List<String> reviews = new ArrayList<>();
		String restName = "";
		int count = 0;
		
		try {
			for (Restaurant oneRest : reviewList) {
					reviews = oneRest.getRestaurantReviews();
				
					for(String review : reviews) {
						//more punctuation can also be removed
						//it is removed, example: "it is nice." here the string split worked on space and the string we got from the splitting is "nice."
						//so . removed
						review = review.replace(".", "");
						review = review.replace(",", "");
						String[] reviewWords = review.split(" ");
						//review words
						for(String reviewWord : reviewWords) {
							if(restConst.GOODWORDS.contains(reviewWord)) {
								count++;
							}
						}
					}
					oneRest.setGoodWordCounts(count);
					count = 0;
			}
			
			Collections.sort(reviewList);
			assignRank(reviewList);
		}catch(Exception e) {
			logger.info("Exception in countTheGoodWords: " + e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * to assign rank dependent on the good words count for the restaurant 
	 * as the list is sorted on good words count a serial ranking is alloted
	 * @param reviewList
	 */
	private void assignRank(ArrayList<Restaurant> reviewList) {
		
		int rank = 1;
		try {
			for(Restaurant oneRest : reviewList) {
				oneRest.setRank(rank);
				rank++;
			}
			
			printRank(reviewList);
		}catch(Exception e) {
			logger.info("Exception in assignRank: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * function is to print rank and restaurant names
	 * @param reviewList
	 */
	private void printRank(ArrayList<Restaurant> reviewList) {
		
		try {
			for(Restaurant oneRest : reviewList) {
				System.out.println(oneRest.getRank() + " : " + oneRest.getRestaurantName());
			}
		}catch(Exception e) {
			logger.info("Exception in assignRank: " + e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * take restaurant reviews as input in an arraylist of type Restaurant which has two attributes
	 * name and the review list
	 * @return
	 */
	public ArrayList<Restaurant> inputReviews() {
		
		ArrayList<Restaurant> restReviewList = new ArrayList<>();
		Restaurant oneRest = new Restaurant();
		List<String> reviews = new ArrayList<>();
		
		//reviews.add("It is good. The food is great.");
		//reviews.add("It is so so. The food is ok.");
		//reviews.add("It is the best one.");
		//reviews.add("It is nice. food is ok.");
		reviews.add("I did not like it.");
		oneRest.setRestaurantReviews(reviews);
		oneRest.setRestaurantName("Paradise");
		restReviewList.add(oneRest);
		
		oneRest = new Restaurant();
		reviews = new ArrayList<>();
		//reviews.add("It is good. The food is great.");
		reviews.add("It is so so the food is ok.");
		reviews.add("It is the best one.");
		//reviews.add("It is nice. food is ok.");
		reviews.add("I did not like it.");
		oneRest.setRestaurantReviews(reviews);
		oneRest.setRestaurantName("Ohri");
		restReviewList.add(oneRest);
		
		oneRest = new Restaurant();
		reviews = new ArrayList<>();
		//reviews.add("It is good. The food is great.");
		reviews.add("It is so so. The food is ok.");
		//reviews.add("It is the best one.");
		//reviews.add("It is nice. food is ok.");
		reviews.add("I did not like it.");
		oneRest.setRestaurantReviews(reviews);
		oneRest.setRestaurantName("Bawarchi");
		restReviewList.add(oneRest);
		
		oneRest = new Restaurant();
		reviews = new ArrayList<>();
		reviews.add("It is good. The food is great.");
		//reviews.add("It is so so. The food is ok.");
		//reviews.add("It is the best one.");
		//reviews.add("It is nice as food is ok.");
		//reviews.add("It is nice as food is ok.");
		reviews.add("I did not like it.");
		oneRest.setRestaurantReviews(reviews);
		oneRest.setRestaurantName("Angara");
		restReviewList.add(oneRest);
		
		return restReviewList;
		
	}
	
	
	
	
}
