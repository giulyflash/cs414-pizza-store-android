package core;

public class Coupon {
	
	private final Integer itemId;
	private int pointValue;
	
	public Coupon(Integer itemId, int pointValue){
		this.itemId = itemId;
		this.pointValue = pointValue;
	}
	
	public Integer getItemId(){
		return itemId;
	}
	
	public int getPointValue(){
		return pointValue;
	}
	
	public void setPointValue(int pointValue){
		this.pointValue = pointValue;
	}

}
