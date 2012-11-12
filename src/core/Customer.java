package core;

import java.util.ArrayList;
import java.util.HashMap;

import util.Address;

public class Customer{
	private String name;
	private Address address;
	private String username;
	private int points;
	private HashMap<Integer, Transaction> orderHistory;

	public Customer(String name, Address address, String username) {
		this.name=name;
		this.address = address;
		this.username = username;
		this.points = 0;
		this.orderHistory = new HashMap<Integer, Transaction>();
	}
	
	public int addPoints(int pointsToAdd){
		points = points + pointsToAdd;
		return points;
	}
	
	public void addTransaction(Transaction transaction){
		orderHistory.put(transaction.getID(), transaction);
	}
	
	public String getName(){
		return name;
	}
	
	public Address getAddress(){
		return address;
	}
	
	public int getPoints(){
		return points;
	}
	
	public ArrayList<Transaction> getOrderHistory(){
		return (ArrayList<Transaction>) orderHistory.values();
	}
	
	public ArrayList<Integer> getOrderHistoryIds(){
		return new ArrayList<Integer>(orderHistory.keySet());
	}
	
	public String getUsername(){
		return username;
	}
	
	public int usePoints(int pointsToUse){
		points = points - pointsToUse;
		return points;
	}
	
	public boolean equals(Customer other){
		return this.username.equals(other.username);
	}
	
}
