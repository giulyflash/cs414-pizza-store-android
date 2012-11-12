package core;

import java.util.ArrayList;
import java.util.HashMap;

import util.Money;

public class CentralTracker{
	/**
	 * 
	 */
	private Menu menu;
	private HashMap<Integer,Transaction> pendingTransactions;
	private HashMap<Integer,Transaction> completeTransactions;
	private HashMap<Integer,Order> pendingOrders;
	private HashMap<Integer,Order> submittedOrders;
	private HashMap<String,Customer> customers;
	private HashMap<Integer,Coupon> coupons;
	private static int ORDER_ID;
	private Money dollarsPerPoint;
	
	/* Constructor */
	
	public CentralTracker(){
		menu = new Menu();
		pendingTransactions = new HashMap<Integer,Transaction>();
		completeTransactions = new HashMap<Integer,Transaction>();
		pendingOrders = new HashMap<Integer,Order>();
		submittedOrders = new HashMap<Integer,Order>();
		customers = new HashMap<String, Customer>();
		coupons = new HashMap<Integer,Coupon>();
		ORDER_ID = 0;
		dollarsPerPoint = new Money("10.00");
	}
	
	/* Menu Methods */
	
	public Menu getMenu(){
		return menu;
	}
	
	/* Order Methods */
	
	public int generateOrderID(){
		return ORDER_ID++;
	}
	
	public ArrayList<Order> getPendingOrders(){
    	return new ArrayList<Order>(pendingOrders.values());
	}
	
    public ArrayList<Order> getSubmittedOrders(){
    	return new ArrayList<Order>(submittedOrders.values());
    }
    
	public ArrayList<Integer> getPendingOrdesrIDs(){
    	return new ArrayList<Integer>(pendingOrders.keySet());
	}
	
    public ArrayList<Integer> getSubmittedOrdersIDs(){
    	return new ArrayList<Integer>(submittedOrders.keySet());
    }
    
    public Order getPendingOrderById(int orderID){
    	return pendingOrders.get(orderID);
    }
    
    public Order getSubmittedOrderById(int orderID){
    	return submittedOrders.get(orderID);
    }
    
    //marks an order as complete
	public void markOrderComplete(int orderID){
		submittedOrders.remove(orderID);
	}
    
	// Moves an order from pending > submitted and creates a pending transaction
    public Transaction submitOrder(int orderID){
    	Order order = pendingOrders.remove(orderID);
    	submittedOrders.put(order.getID(),order);
		Transaction transaction = new Transaction(order);
		// will subtrack points from customer if coupon present
		// and add transaction to customer orderHistory
		order.useCoupon();
		order.getCustomer().addTransaction(transaction);
		pendingTransactions.put(order.getID(),transaction);
		return transaction;
    }

    /**
     *  
     * @param order
     */
    public void addOrderToPending(Order order){
    	pendingOrders.put(order.getID(),order);
    }
    
    
	/* Transaction Methods */
	
	public ArrayList<Transaction> getPendingTransactions(){
		return new ArrayList<Transaction>(pendingTransactions.values());
	}
	
	public ArrayList<Integer> getPendingTransactionsID(){
		return new ArrayList<Integer>(pendingTransactions.keySet());
	}
	
	public ArrayList<Transaction> getCompleteTransactions(){
		return new ArrayList<Transaction>(completeTransactions.values());
	}
	
	public ArrayList<Integer> getCompleteTransactionsID(){
		return new ArrayList<Integer>(completeTransactions.keySet());
	}
	
	public Transaction getTransaction(int transactionID){
		if(pendingTransactions.containsKey(transactionID))
			return pendingTransactions.get(transactionID);
		else 
			return completeTransactions.get(transactionID);
	}
	
	public Transaction getPendingTransaction(int transactionID){
		return pendingTransactions.get(transactionID);
	}
	
	public Transaction getCompleteTransaction(int transactionID){
		return completeTransactions.get(transactionID);
	}
	
    public void markTransactionComplete(Transaction transaction){
    	pendingTransactions.remove(transaction.getID());
    	completeTransactions.put(transaction.getID(),transaction);
    	//points are added to the customer's running total once transaction is complete
    	int pointsToAdd = (int) (transaction.getOrder().getTotal().getQuantity()/dollarsPerPoint.getQuantity());
    	transaction.getOrder().getCustomer().addPoints(pointsToAdd);
    }
    
    /* Coupon Methods */
    public void addCoupon(Coupon coupon){
    	coupons.put(coupon.getItemId(), coupon);
    }
    
    public ArrayList<Integer> getCouponIds(){
    	return new ArrayList<Integer>(coupons.keySet());
    }
    
    public ArrayList<Integer> getCustomerCouponIds(Customer customer){
    	int customerPoints = customer.getPoints();
    	ArrayList<Integer> customerCoupons = new ArrayList<Integer>();
    	for(Coupon coupon:coupons.values()){
    		if(coupon.getPointValue() <= customerPoints){
    			customerCoupons.add(coupon.getItemId());
    		}
    	}
    	return customerCoupons;
    }
    
    public Coupon getCouponById(int id){
    	return coupons.get(id);
    }
    
    public Money getDollarsPerPoint(){
    	return dollarsPerPoint;
    }
    
    public void setDollarsPerPoint(Money dollars, int points){
    	dollarsPerPoint = new Money(dollars.getQuantity()/points);
    }
    
    public void removeCoupon(int id){
    	coupons.remove(id);
    }
    
	/* Customer Methods */
	
    public void addCustomer(Customer customer){
    	customers.put(customer.getUsername(), customer);
    }
    
    public Customer getCustomerByUsername(String username){
    	return customers.get(username);
    }
    
    public boolean customerExists(String username){
    	return customers.containsKey(username);
    }
    
    public ArrayList<Integer> getCustomerOrderHistory(String username){
    	return customers.get(username).getOrderHistoryIds();
    }

}
