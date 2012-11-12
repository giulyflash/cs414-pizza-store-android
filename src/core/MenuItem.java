package core;

import util.Money;

public class MenuItem{
	private String description;
	private Integer id;
	private String name;
	private Money price;
	private MenuItemType type; //can be pizza, drink, other (dipping sauce, parmesan, etc.), side (breadsticks, cinnasticks)
	
	public MenuItem(String name, Integer id, Money price, MenuItemType type, String description){
		this.name = name;
		this.price = price;
		this.id = id;
		this.type = type;
		this.description = description;
	}
	
	public MenuItem(String name, Integer id, Money price, MenuItemType type){
		this(name, id, price, type, "");
	}
	
	public MenuItem(String name, Integer id, Money price){
		this(name, id, price, MenuItemType.OTHER, "");
	}
	
	public String getDescription(){
		return description;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public Money getPrice(){
		return price;
	}
	
	public MenuItemType getType(){
		return type;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(Money price){
		this.price = price;
	}
	
	public void setType(MenuItemType type){
		this.type=type;
	}
	
	public boolean equals(MenuItem other){
		return this.id.equals(other.id)
				&& this.price.equals(other.price)
				&& this.type.equals(other.type)
				&& this.name.equals(other.name)
				&& this.description.equals(other.description);
	}
}
