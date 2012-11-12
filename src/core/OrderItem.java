package core;

public class OrderItem{

	private Integer id;
	private int quantity;
	
	public OrderItem(Integer id, int quantity){
		this.id = id;
		this.quantity = quantity;
	}
	
	public Integer getId(){
		return id;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public boolean equals(OrderItem other){
		return this.id.equals(other.id) && this.quantity == other.quantity;
	}
	
	public String toString(){
		return id.toString();
	}
	
}
