package com.project.vendingMachine;


/**
 * @author Swathi
 *
 */
public enum Product {
	
	COLA("Cola", 100) ,CHIPS("Chips",50),CANDY("Candy",65);
	
	private String name;
	private int price;
	
	private Product(String name ,int price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}
