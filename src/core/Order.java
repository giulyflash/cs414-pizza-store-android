package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import util.Money;

public class Order{
	private Customer customer;
	private HashMap<Integer,OrderItem> orderItems;
	private Coupon coupon;
	private OrderItem couponItem;
	private boolean couponUsed;
	private Menu menu;
	private boolean delivery;
	private final int ID;
	
	public Order(int id, Customer customer, Menu menu, boolean delivery){
		this.ID = id;
		this.customer = customer;
		this.menu = menu;
		this.delivery = delivery;
		this.orderItems = new HashMap<Integer, OrderItem>();
		this.couponUsed = false;
		this.coupon = null;
		this.couponItem = null;
	}
	
	public Order(int id, Customer customer, Menu menu){
		this(id, customer, menu, false);
	}
	
	public void addItem(OrderItem item){
		orderItems.put(item.getId(), item);
	}
	
	public void addItems(ArrayList<OrderItem> items){
		for(OrderItem item:items){
			this.addItem(item);
		}
	}
	
	public int getID(){
		return ID;
	}
	
	public Coupon getCoupon(){
		return coupon;
	}
	
	public boolean getCouponUsed(){
		return couponUsed;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public boolean getDelivery(){
		return delivery;
	}
	
	public ArrayList<OrderItem> getItems(){
		ArrayList<OrderItem> result = new ArrayList<OrderItem>(orderItems.values());
		if(couponUsed){
			result.add(coupon.getItemId(), couponItem);
		}
		return result;
	}
	
	public ArrayList<Integer> getItemIds(){
		ArrayList<Integer> result = new ArrayList<Integer>(orderItems.keySet());
		if(couponUsed){
			result.add(coupon.getItemId());
		}
		return result;
	}
	
	public OrderItem getItemByID(int id){
		return orderItems.get(id);
	}
	
	public Money getTotal(){
		Money total = new Money(0);
		for(Entry<Integer, OrderItem> item:orderItems.entrySet()){
			double price = menu.getMenuItemById(item.getValue().getId()).getPrice().getQuantity() * item.getValue().getQuantity();
			String currency = menu.getMenuItemById(item.getValue().getId()).getPrice().getCurrency();
			total = total.add(new Money(currency, price));
		}
		return total;
	}
	
	
	public int numberOfItems(){ 
		return orderItems.size();
	}
	
	public void removeAll(){
		orderItems.clear();
		coupon = null;
		couponUsed = false;
	}
	
	public void removeCoupon(){
		coupon = null;
		couponItem = null;
		couponUsed = false;
	}
	
	public void removeItem(OrderItem item){
		orderItems.remove(item.getId());
	}

	public void removeItems(ArrayList<OrderItem> items){
		for(OrderItem item:items){
			this.removeItem(item);
		}
	}
	
	public void setCoupon(Coupon coupon){
		if(coupon != null){
			this.coupon = coupon;
			this.couponItem = new OrderItem(coupon.getItemId(), 1);
			couponUsed = true;
		}
	}
	
	public void setDelivery(boolean delivery){
		this.delivery = delivery;
	}
	
	public void useCoupon(){
		if(couponUsed){
			customer.usePoints(coupon.getPointValue());
		}
	}
	
	public String toString(){
		String result = "";
		if(couponUsed){
			MenuItem coupon = menu.getMenuItemById(this.coupon.getItemId());
			result += "Free " + coupon.getName() + "\t$0.00\tx1\n";
		}
		for(Entry<Integer, OrderItem> orderEntry:orderItems.entrySet()){
			OrderItem orderItem = orderEntry.getValue();
			MenuItem menuItem = menu.getMenuItemById(orderItem.getId());
			result +=  menuItem.getName() + "\t" + menuItem.getPrice() + "\tx" + orderItem.getQuantity() + "\n";
		}
		result += "-------------\n";
		result += "total: " + getTotal();
		return result;
	}

	public String toHTMLString(){
		String result = "<html><table><tr><td>Name</td><td>Price</td><td>Quantity</td></tr>";
		if(couponUsed){
			MenuItem coupon = menu.getMenuItemById(this.coupon.getItemId());
			result += "<tr><td>Free " + coupon.getName() + "</td><td> $0.00 </td><td> 1</td></tr>";
		}
		for(Entry<Integer, OrderItem> orderEntry:orderItems.entrySet()){
			OrderItem orderItem = orderEntry.getValue();
			MenuItem menuItem = menu.getMenuItemById(orderItem.getId());
			result +=  "<tr><td>" + menuItem.getName() + "</td><td> " + menuItem.getPrice() + " </td><td> " + orderItem.getQuantity() + "</td></tr>";
		}
		result += "</table>";
		result += "<hr />";
		result += "total: " + getTotal();
		result += "</html>";
		return result;
	}

}
