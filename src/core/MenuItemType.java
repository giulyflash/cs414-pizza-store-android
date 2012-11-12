package core;

public enum MenuItemType {
	PIZZA("Pizza"), DRINK("Drink"), SIDE("Side"), OTHER("Other");
	
	private final String display;
	
	private MenuItemType(String display){
		this.display = display;
	}
	
	public String toString(){
		return display;
	}
}
