package com.Planon.TypeCasting;

interface MovableObjects {
	int price = 10;

	default void showPrice() {
		System.out.println("In Vehicle " + price);
	}
}

interface Vehicle extends MovableObjects {

	default void showPrice() {
		System.out.println("In Vehicle " + price);
	}
}

class Cars implements Vehicle {
	int price = 20;

	public void showPrice() {
		System.out.println("In Cars " + price);
	}
}

class Manualcars extends Cars {
	int price = 30;

	
	  public void showPrice() { System.out.println("In Manualcars " + price); }
	 
}

class Petrolcars extends Manualcars {
	public void showPrice() {
		System.out.println(" In Petrolcars" + price);
	}
}

public class DownCastingExample {

	public static void main(String[] args) {

		Vehicle v = new Manualcars();
		System.out.println(v.price);
		((Cars) v).showPrice();
		((Petrolcars) v).showPrice();
	}

}

