package util;

import java.io.Serializable;

public class CreditPayment extends Payment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private String expirationDate;
	
	
	public CreditPayment(String cardNumber,  String expirationDate){
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
	}

	public String getCardNumber(){
		return cardNumber;
	}
	
	public String getExpirationDate(){
		return expirationDate;
	}
	
	//NOTE: untested equals, using super.equals(other)
	public boolean equals(CreditPayment other){
		return this.cardNumber.equals(other.cardNumber) && this.expirationDate.equals(other.expirationDate) && super.equals(other);
	}

	public String toString(){
		return "Paid with card " + super.toString();
		
	}

}
