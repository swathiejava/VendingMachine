package com.project.vendingMachine;

/**
 * @author Swathi
 *
 */
public enum Price {
	PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
	
	private int value;
	
	private Price(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	
	

}
