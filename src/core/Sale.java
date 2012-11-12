package core;

import util.Money;
import util.Payment;

public class Sale {
	
	private Money cost;
	private Order order;
	private Payment payment;
	
	public Sale (Order order, Payment payment){
		this.order=order;
		this.payment = payment;
		this.cost = order.getTotal();
	}
	
	public String generateReceipt(){
		//item name		price		quantity   
		//-----------------
		//total
		//
		//payment
		
		String receipt = order.toString();
		receipt+= "\n";
		
		receipt+=payment.toString();	
		
		return receipt;
	}
	
	public Money getCost(){
		return cost;
	}
	
	public Payment getPayment(){
		return payment;
	}
	
	public Order getOrder(){
		return order;
	}
}
