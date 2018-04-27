package com.project.vendingMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Program for vending machine
 * @author Swathi
 *
 */
import java.util.Scanner;

public class VendingMachine {
		
	private Map<Product, Integer> stock = new HashMap<Product, Integer>();
	private Map<Price, Integer> cashInMachine = new HashMap<Price, Integer>();
	private int balance;
	private int amountRequired = 0;
	private int insertedAmount = 0;
	private String selectedProduct = "";
	private String returnCoins;
	
	 public VendingMachine(){
	 }
	 
	 public static void main(String args[]){
		VendingMachine vm = new VendingMachine();
		vm.vendingProduct();
		}
	 
	 public void vendingProduct(){		
		//load items of each product and each cash item
		 for(Price p : Price.values()){
			 cashInMachine.put(p, 5); 
				} 
		 for(Product s : Product.values()){ 
				stock.put(s, 5); 
				}
		while(true){
		insertedAmount = 0;
		Scanner input = new Scanner(System.in);
		//Select Product
		System.out.println("Select Product from the options: Cola, Chips, Candy");
		selectedProduct = input.next();
		// checks if exact change is required for purchase
		checkIfExactChangeRequired();
		System.out.println("Insert Coins as 5 10 25 .Press # to submit your coins for purchase");
		while (input.hasNext()) {
    		if ((input.hasNextInt())){
    			int amt = input.nextInt();
    			if((amt == 5 || amt ==10 || amt == 25)){
    			addCashToMachine(amt);
    			this.insertedAmount = this.insertedAmount + amt;
    			}
    			else{
    				System.out.println("Please insert "
    						+ "Nickels,Dimes or Quarters");
    				break;
    			}
    		}
    		else if (input.next().equals("#"))
    			break;
    	}
		if (insertedAmount>0){
			
			if(selectedProduct.equals(Product.COLA.getName())) {
				amountRequired = Product.COLA.getPrice();
				if (checkProductExists(Product.COLA))
				buyProduct(amountRequired,Product.COLA,this.insertedAmount);

			}
			else if(selectedProduct.equals(Product.CHIPS.getName())) {
				amountRequired = Product.CHIPS.getPrice();
				if (checkProductExists(Product.CHIPS))
				buyProduct(amountRequired,Product.CHIPS,this.insertedAmount);

			}
			else if(selectedProduct.equals(Product.CANDY.getName())) {
				amountRequired = Product.CANDY.getPrice();
				if (checkProductExists(Product.CANDY))
				buyProduct(amountRequired,Product.CANDY,this.insertedAmount);
			}
			else{
				System.out.println("Invalid Selection. Please select from options");
			}
		}
		
		}		
	}
/*
 * Method to perform buy a product operation
 * and update stock and cash of the machine
 */
	public void buyProduct(int amountRequired, Product selectedProduct, int insertedAmount){
		
		if(insertedAmount >= amountRequired){
			this.balance = insertedAmount - amountRequired;
			collectChange(balance);
			int count = stock.get(selectedProduct);
			stock.put(selectedProduct, count - 1);
			System.out.println("Please take your change " + balance +" cents");
			System.out.println("ThankYou for your purchase");
		}	
		else {
			System.out.println("Insert more coins. Price of the product is :"+ amountRequired );
		}				
					
	}
/*
 * Method to collect change
 * 
 */
	
	public void collectChange(int balance){
		
		if(balance > 0) {
			while(balance > 0){ 
				  if(balance/Price.QUARTER.getValue()> 0&& cashInMachine.containsKey(Price.QUARTER))
				  { 
					  balance = balance - Price.QUARTER.getValue(); 
					  int count = cashInMachine.get(Price.QUARTER);
					  System.out.println("Coin Dispensed " + Price.QUARTER.getValue() );
					  cashInMachine.put(Price.QUARTER, count - 1);
					  continue; 
					}else if(balance/Price.DIME.getValue()>0 && cashInMachine.containsKey(Price.DIME)) 
					{ 
						balance = balance - Price.DIME.getValue(); 
						int count = cashInMachine.get(Price.DIME); 
						System.out.println("Coin Dispensed " + Price.DIME.getValue() );
						cashInMachine.put(Price.DIME, count - 1);
						continue; 
					}else if(balance/Price.NICKEL.getValue()>0 && cashInMachine.containsKey(Price.NICKEL)){ 
						balance = balance - Price.NICKEL.getValue();
						int count = cashInMachine.get(Price.NICKEL); 
						System.out.println("Coin Dispensed " + Price.NICKEL.getValue() );
						cashInMachine.put(Price.NICKEL, count - 1);
						continue; 
					}else{ 
					    System.out.println("Not enough change. Please try another product");
					    } 				  
		}
			
		}
	}
	
/*
 * Method to return the coins incase if user has 
 * second thoughts of the purchase	
 */
	
	public void returnCoins() {
		System.out.println("Press return coins button");
		balance = this.insertedAmount;
		collectChange(balance);
		System.out.println("Please take your change " + balance +" cents");
		System.out.println("Insert Coins for next purchase");
				
	}
	
/*
 * Method to check if the Product exists in the machine
 * 
 */
	public boolean checkProductExists(Product selectedProduct) {
		boolean flag = false;
		Integer value = stock.get(selectedProduct); 
		if(value == null || value == 0) {
			System.out.println("Item Sold out");
		}
		else{
			flag = true;
		}
		return flag;
		}

/*
 * Method to check if exact change required for purchase
 *
 */
   public void checkIfExactChangeRequired() {	   
	   if((cashInMachine.get(Price.DIME)== 0) || (cashInMachine.get(Price.DIME)== 0)|| 
			   (cashInMachine.get(Price.NICKEL)==null) || (cashInMachine.get(Price.NICKEL)== null)){
			System.out.println("Not enough change.Please insert exact change.");

	   }
	   
   }
/*
 * Method to add inserted coins to cash in machine
 */
   public void addCashToMachine(int amount) {
	   switch(amount){
	    case 25:addCash(Price.QUARTER);
	    		break;
	    case 10:addCash(Price.DIME);
	    		break;
	    case 5: addCash(Price.NICKEL);
	    		break;
	    default: break;	   
	   }
   }
	   
	  public void addCash(Price p){
		  int count = cashInMachine.get(p); 
  		  cashInMachine.put(p, count+1);
	  }
	  
}