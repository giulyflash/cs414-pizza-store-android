package util;

import java.io.Serializable;

public class CashPayment extends Payment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Money amountPaid;
	private Money changeDue;
	
	public CashPayment(Money amountPaid, Money amountDue){
		super();
		this.amountPaid = amountPaid;
		this.changeDue = amountPaid.subtract(amountDue);
	}
	
	public CashPayment(String amountPaid, String amountDue){
		this(new Money(amountPaid), new Money(amountDue));
	}
	
	public Money getAmountPaid(){
		return amountPaid;
	}
	
	public Money getChangeDue(){
		return changeDue;
	}
	
	//NOTE: untested equals, using super.equals(other)
	public boolean equals(CashPayment other){
		return this.amountPaid.equals(other.amountPaid) && this.changeDue.equals(other.changeDue) && super.equals(other);
	}
	
	public String toString(){
		String result = "Amount paid: " + amountPaid.toString() + "\n";
		result += "Change returned: " + changeDue.toString() + "\n";
		result += super.toString();
		return result;
	}

}
