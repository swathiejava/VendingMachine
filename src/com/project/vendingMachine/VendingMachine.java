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
			List<Price> changesList = collectChange(balance);
			for(Price p : changesList){
				int count = cashInMachine.get(p); 
				cashInMachine.put(p, count - 1);
			}
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
	
	public List<Price> collectChange(int balance){
		List<Price> changes = Collections.emptyList();
		
		if(balance > 0) {
			changes = new ArrayList<Price>();
			long avialableBalance = balance;
			while(avialableBalance > 0){ 
				  if(avialableBalance >= Price.QUARTER.getValue()&& cashInMachine.containsKey(Price.QUARTER))
				  { 
					  changes.add(Price.QUARTER); 
					  avialableBalance = avialableBalance - Price.QUARTER.getValue(); 
					  continue; 
					}else if(balance >= Price.DIME.getValue() && cashInMachine.containsKey(Price.DIME)) 
					{ 
						changes.add(Price.DIME); 
						avialableBalance = avialableBalance - Price.DIME.getValue(); 
						continue; 
					}else if(balance >= Price.NICKEL.getValue() && cashInMachine.containsKey(Price.NICKEL)){ 
						changes.add(Price.NICKEL); 
						avialableBalance = avialableBalance - Price.NICKEL.getValue(); 
						continue; 
					}else{ 
					    System.out.println("Not enough change. Please try another product");
					    } 				  
		}
			
		}
		return changes;
	}
	
/*
 * Method to return the coins incase if user has 
 * second thoughts of the purchase	
 */
	
	public void returnCoins() {
		System.out.println("Press return coins button");
		balance = this.insertedAmount;
		List<Price> changesList = collectChange(balance);
		for(Price p : changesList){
			int count = cashInMachine.get(p); 
			cashInMachine.put(p, count - 1);
		}
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