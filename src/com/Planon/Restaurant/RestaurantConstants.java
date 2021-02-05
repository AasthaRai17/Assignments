package com.Planon.Restaurant;

import java.util.ArrayList;

public class RestaurantConstants {
	public final String GOOD1 = "nice";
	public final String GOOD2 = "good";
	public final String GOOD3 = "awesome";
	public final String GOOD4 = "best";
	public final String GOOD5 = "great";
	public ArrayList<String> GOODWORDS = new ArrayList<>();
	
	public RestaurantConstants() {
		GOODWORDS.add(GOOD1);
		GOODWORDS.add(GOOD2);
		GOODWORDS.add(GOOD3);
		GOODWORDS.add(GOOD4);
		GOODWORDS.add(GOOD5);
	}
}
