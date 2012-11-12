package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Payment {
	
	private Date date;
	
	public Payment(){
		this.date = new Date();
	}
	
	public Payment(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getDateString(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return dateFormat.format(date).toString();
	}
	
	public boolean equals(Payment other){
		return this.date.equals(other.date);
	}
	
	public String toString(){
		return getDateString();
	}

}
