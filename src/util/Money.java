package util;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Money implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currency;
	private double quantity; //always positive
	private boolean negative; //keep track of +/- separate from quantity for toString(); method
	
	public Money(String currency, double quantity){
		if(quantity<0){
			this.negative = true;
			quantity = quantity*(-1);
		}
		else this.negative = false;
		this.currency = currency;
		this.quantity = quantity;
	}
	
	public Money(double quantity){
		this("$", quantity);
	}
	
	//accepts +/-, and strings with or without currency
	public Money(String money){
		char first = money.charAt(0);
		if (first== '-'){
			this.negative = true;
			money = money.substring(1);
		}
		else this.negative=false;
		char second = money.charAt(0);
		if(second >= '0' && second <= '9'){
			this.currency = "$";
		}
		else{
			this.currency = second +"";
			money = money.substring(1);
		}
		this.quantity = Double.parseDouble(money);
	}

	public String getCurrency(){
		return currency;
	}
	
	public double getQuantity(){
		if(negative){
			return quantity * (-1);
		}
		return quantity;
	}
	
	public String toString(){
		//note: formatting is proper for $ values only
		DecimalFormat df = new DecimalFormat("0.00");
		String sign = (negative) ? "-" : "";
		return sign + currency + df.format(quantity);
	}
	
//operator overloading: 	
//precondition - same currency
//will use this.currency but not enforce the precondition
	
	public Money subtract(Money other){
		return new Money(this.getCurrency(), this.getQuantity() - other.getQuantity());
	}
	
	public Money add(Money other){
		return new Money(this.getCurrency(), this.getQuantity() + other.getQuantity());
	}
	
	public Money multiply(Money other){
		return new Money(this.getCurrency(), this.getQuantity() * other.getQuantity());
	}
	
	public Money divide(Money other){
		return new Money(this.getCurrency(), this.getQuantity() / other.getQuantity());
	}
	
	public boolean equals(Money other){
		return this.currency.equals(other.currency) && this.quantity == other.quantity;
	}

}
