package util;

import java.io.Serializable;

public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String street;
	private String cityState;
	private String zip;
	
	public Address(String street, String cityState, String zip){
		this.street = street;
		this.cityState = cityState;
		this.zip = zip;
	}
	
	public void setCity(String cityState){
		this.cityState = cityState;
	}
	
	public void setStreet(String street){
		this.street = street;
	}
	
	public void setZip(String zip){
		this.zip = zip;
	}
	
	public String getCityState(){
		return cityState;
	}
	
	public String getStreet(){
		return street;
	}
	
	public String getZip(){
		return zip;
	}

	public boolean equals(Address other){
		return this.street.equals(other.street) 
				&& this.cityState.equals(other.cityState) 
				&& this.zip.equals(other.zip);
	}


}
