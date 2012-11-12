package core;

import util.CashPayment;
import util.CreditPayment;
import util.Money;

public class Transaction{
	private static final int PENDING = 0;
	private static final int COMPLETE = 1;
	private static final int INCOMPLETE = -1;
	
	private Order order;
	private Sale sale;
	private int status;
	private final int ID;
	/*
	 * ‘pending’ once it’s been created and it’s waiting to be resolved;
	 * ‘incomplete’ if the transaction is canceled
	 * ‘complete’ if payment has been received
	 */

	public Transaction(Order order){
		this.order=order;
		this.ID = order.getID();
		this.status = PENDING;
		this.sale = null;
	}
	
	public int getID(){
		return ID;
	}
	
	public Order getOrder(){
		return order;
	}
	
	public Sale getSale(){
		return sale;
	}
	
	public boolean isPending(){
		return status==PENDING;
	}
	
	public boolean isComplete(){
		return status==COMPLETE;
	}
	
	public boolean isIncomplete(){
		return status==INCOMPLETE;
	}
	
	public void markIncomplete(){
		this.status = INCOMPLETE;
	}
	
	private String renderPayment(){
		this.status = COMPLETE;
		return sale.generateReceipt();
	}
	
	//creates cash payment, links it to this.sale, marks complete and creates receipt to return
	public String renderCashPayment(String amount){
		this.sale = new Sale(order, new CashPayment(new Money(amount), order.getTotal()));
		return this.renderPayment();
	}

	//creates cash payment, links it to this.sale, marks complete and creates receipt to return
	public String renderCreditPayment(String cardNumber, String expirationDate){	
		this.sale = new Sale(order, new CreditPayment(cardNumber, expirationDate));
		return this.renderPayment();
	}
}
